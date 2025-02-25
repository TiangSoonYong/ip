package doraemon;

import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.InvalidKeywordException;
import doraemon.exceptions.NoByPrefixException;
import doraemon.exceptions.NoByStringException;
import doraemon.exceptions.NoDescriptionException;
import doraemon.exceptions.NoFromPrefixException;
import doraemon.exceptions.NoFromStringException;
import doraemon.exceptions.NoToPrefixException;
import doraemon.exceptions.NoToStringException;
import doraemon.task.Deadline;
import doraemon.task.Event;
import doraemon.task.Task;
import doraemon.task.TaskType;
import doraemon.task.ToDo;

import java.util.ArrayList;

public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";

    private final ArrayList<Task> tasks = new ArrayList<>();

    public String addTask(String commandArgs, TaskType taskType) throws AddTaskException {
        switch (taskType) {
        case TODO:
            addToDo(commandArgs);
            break;
        case DEADLINE:
            addDeadline(commandArgs);
            break;
        case EVENT:
            addEvent(commandArgs);
            break;
        default:
            // Throw exception
        }
        return "Got it. I've added this task:" +
                "\n\t\t" + tasks.get(tasks.size() - 1) +
                "\n\t Now you have " + tasks.size() + " tasks in the list.";
    }

    private void addToDo(String commandArgs) throws AddTaskException {
        String description = commandArgs;
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        tasks.add(tasks.size(), new ToDo(description));
    }

    private void addDeadline(String commandArgs) throws AddTaskException {
        final int indexOfByPrefix = commandArgs.indexOf(DATA_PREFIX_BY);
        if (indexOfByPrefix < 0) {
            throw new NoByPrefixException();
        }
        String description = commandArgs.substring(0, indexOfByPrefix).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        String by = removePrefixSign(commandArgs.substring(indexOfByPrefix, commandArgs.length()), DATA_PREFIX_BY).trim();
        if (by.isEmpty()) {
            throw new NoByStringException();
        }
        tasks.add(tasks.size(), new Deadline(description, by));
    }

    private void addEvent(String commandArgs) throws AddTaskException {
        final int indexOfFromPrefix = commandArgs.indexOf(DATA_PREFIX_FROM);
        final int indexOfToPrefix = commandArgs.indexOf(DATA_PREFIX_TO);
        if (indexOfFromPrefix < 0) {
            throw new NoFromPrefixException();
        }
        if (indexOfToPrefix < 0) {
            throw new NoToPrefixException();
        }
        int indexOfFirstPrefix = Math.min(indexOfFromPrefix, indexOfToPrefix);
        String description;
        String from;
        String to;

        description = commandArgs.substring(0, indexOfFirstPrefix).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        if (indexOfFromPrefix < indexOfToPrefix) { // Description - From - To
            from = removePrefixSign(commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                    DATA_PREFIX_FROM).trim();
            to = removePrefixSign(commandArgs.substring(indexOfToPrefix, commandArgs.length()),
                    DATA_PREFIX_TO).trim();

        } else { // Description - To - From
            from = removePrefixSign(commandArgs.substring(indexOfFromPrefix, commandArgs.length()),
                    DATA_PREFIX_FROM).trim();
            to = removePrefixSign(commandArgs.substring(indexOfToPrefix, indexOfFromPrefix),
                    DATA_PREFIX_TO).trim();
        }

        if (from.isEmpty()) {
            throw new NoFromStringException();
        }
        if (to.isEmpty()) {
            throw new NoToStringException();
        }
        tasks.add(tasks.size(), new Event(description, from, to));
    }

    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    public String getTasks() {
        if (tasks.isEmpty()) {
            return "You do not have any tasks in your list";
        }
        String message = "Here are the tasks in your list:";
        for (Task task : tasks) {
            message += "\n\t " + String.format("%d. ", tasks.indexOf(task) + 1) + task;
        }
        return message;
    }

    public String setIsDone(int taskIndex, boolean isDone) {
        Task selectedTask;
        try {
            selectedTask = tasks.get(taskIndex);
            selectedTask.setDone(isDone);
        } catch (Exception e) {
            return "Task " + (taskIndex + 1) + " does not exist";
        }
        if (isDone) {
            return "Nice! I've marked this task as done:\n\t\t " + selectedTask;
        } else {
            return "Nice! I've marked this task as not done yet:\n\t\t " + selectedTask;
        }
    }

    public String deleteTask(int taskIndex) {
        Task selectedtask;
        try {
            selectedtask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
        } catch (Exception e) {
            return "Task " + (taskIndex + 1) + " does not exist";
        }
        return "Noted. I've removed this task" +
                "\n\t\t" + selectedtask +
                "\n\t Now you have " + tasks.size() + " tasks in the list.";
    }

    public String findTasks(String keyword) throws InvalidKeywordException {
        if (keyword.isEmpty()) {
            throw new InvalidKeywordException();
        }
        String message = "Here are the matching tasks in your list";
        boolean hasMatch = false;
        for (Task task: tasks) {
            if (task.hasKeyword(keyword)) {
                message += "\n\t " + String.format("%d. ", tasks.indexOf(task) + 1) + task;
                hasMatch = true;
            }
        }
        if (!hasMatch) {
            return "There are no matching tasks in your list";
        }
        return  message;
    }

    public String clearTasks() {
        tasks.clear();
        return "All tasks has been cleared";
    }

    public String readTasksFromFile(Storage storage) {
        return storage.readTasksFromFile(tasks);
    }

    public String saveTasksAsFile(Storage storage) {
        return storage.saveTasksAsFile(tasks);
    }
}
