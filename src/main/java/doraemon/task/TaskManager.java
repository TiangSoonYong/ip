package doraemon.task;

import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.NoByPrefixException;
import doraemon.exceptions.NoByStringException;
import doraemon.exceptions.NoDescriptionException;
import doraemon.exceptions.NoFromPrefixException;
import doraemon.exceptions.NoFromStringException;
import doraemon.exceptions.NoToPrefixException;
import doraemon.exceptions.NoToStringException;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMITER = " \\| ";
    private static final String DONE_ICON = "X";

    private final ArrayList<Task> tasks = new ArrayList<>();

    public String addTask(String commandArgs, TaskType taskType) {
        try {
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
        } catch (AddTaskException e) {
            return e.getErrorMessage();
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
        if (tasks.size() == 0) {
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

    public String clearTasks() {
        tasks.clear();
        return "All tasks has been cleared";
    }

    public String readTasksFromFile() {
        File f = new File(FILE_DIRECTORY + FILE_NAME);
        Scanner s;
        String message = "Loading Tasks.txt\n\t ";
        boolean hasError = false;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            return message + "Tasks.txt not found!\n\t ";
        }
        while (s.hasNext()) {
            Task temp;
            try {
                String[] commandArgs = s.nextLine().split(DELIMITER);
                temp = parseTasksFromText(commandArgs);
            } catch (Exception e) {
                if (!hasError) {
                    message += "Format Error found\n\t ";
                    hasError = true;
                }
                continue;
            }
            tasks.add(tasks.size(), temp);
        }
        if (hasError) {
            message += "Restoring file to proper format\n\t ";
            message += saveTasksAsFile() + "\n\t ";
        }
        return message + "Tasks.txt read successfully";
    }

    private static Task parseTasksFromText(String[] commandArgs) throws Exception {
        Task temp;
        String taskType = commandArgs[0];
        String taskStatus = commandArgs[1];
        String description = commandArgs[2];
        switch (taskType) {
        case "T":
            temp = new ToDo(description);
            break;
        case "D":
            String by = commandArgs[3];
            temp = new Deadline(description, by);
            break;
        case "E":
            String from = commandArgs[3];
            String to = commandArgs[4];
            temp = new Event(description, from, to);
            break;
        default:
            throw new Exception();
        }
        temp.setDone(taskStatus.equals(DONE_ICON));
        return temp;
    }

    public String saveTasksAsFile() {
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(FILE_DIRECTORY, FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                String textToAdd = task.getTaskAsText();
                fw.write(textToAdd + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            return "Tasks.txt failed to save";
        }
        return "Tasks.txt saved successfully" + "\n\t full path: " + file.getAbsolutePath();
    }
}
