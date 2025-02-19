package doraemon.task;

import doraemon.exceptions.NoByPrefixException;
import doraemon.exceptions.NoByStringException;
import doraemon.exceptions.NoDescriptionException;
import doraemon.exceptions.NoFromPrefixException;
import doraemon.exceptions.NoFromStringException;
import doraemon.exceptions.NoToPrefixException;
import doraemon.exceptions.NoToStringException;

import java.util.ArrayList;

public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";

    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

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
        } catch (NoDescriptionException e) {
            return "The description is empty!";
        } catch (NoByPrefixException e) {
            return "By prefix [/by] is missing!";
        } catch (NoByStringException e) {
            return "The deadline is empty!";
        } catch (NoFromPrefixException e) {
            return "From prefix [/from] is missing!";
        } catch (NoFromStringException e) {
            return "The start date is missing!";
        } catch (NoToPrefixException e) {
            return "To prefix [/to] is missing!";
        } catch (NoToStringException e) {
            return "The end date is missing!";
        }

        taskCount++;
        return "Got it. I've added this task:" +
                "\n\t\t" + tasks.get(taskCount - 1) +
                "\n\t Now you have " + taskCount + " tasks in the list.";
    }

    private void addToDo(String commandArgs)
            throws NoDescriptionException {
        String description = commandArgs;
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        tasks.add(taskCount, new ToDo(description));
    }

    private void addDeadline(String commandArgs)
            throws NoDescriptionException, NoByPrefixException, NoByStringException {
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
        tasks.add(taskCount, new Deadline(description, by));
    }

    private void addEvent(String commandArgs)
            throws NoDescriptionException, NoFromPrefixException, NoFromStringException,
            NoToPrefixException, NoToStringException {
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
            from = removePrefixSign(
                    commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
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
        tasks.add(taskCount, new Event(description, from, to));
    }

    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    public String getTasks() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskCount; i++) {
            message += "\n\t " + String.format("%d. ", i + 1) + tasks.get(i);
        }
        return message;
    }

    public String setIsDone(int taskIndex, boolean isDone) {
        try {
            tasks.get(taskIndex).setDone(isDone);
        } catch (Exception e) {
            return "Task " + (taskIndex + 1) + " does not exist";
        }
        if (isDone) {
            return "Nice! I've marked this task as done:\n\t\t " + tasks.get(taskIndex);
        } else {
            return "Nice! I've marked this task as not done yet:\n\t\t " + tasks.get(taskIndex);
        }
    }

    public String deleteTask(int taskIndex) {
        String task;
        try {
            task = String.valueOf(tasks.get(taskIndex));
            tasks.remove(taskIndex);
            taskCount--;
        } catch (Exception e) {
            return "Task " + (taskIndex + 1) + " does not exist";
        }
        return "Noted. I've removed this task" +
                "\n\t\t" + task +
                "\n\t Now you have " + taskCount + " tasks in the list.";
    }
}
