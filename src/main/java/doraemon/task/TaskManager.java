package doraemon.task;

import doraemon.exceptions.NoByPrefixException;
import doraemon.exceptions.NoByStringException;
import doraemon.exceptions.NoDescriptionException;
import doraemon.exceptions.NoFromPrefixException;
import doraemon.exceptions.NoFromStringException;
import doraemon.exceptions.NoToPrefixException;
import doraemon.exceptions.NoToStringException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";
    private static final int MAX_TASKS_SIZE = 100;
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMITER = " | ";

    private final Task[] tasks = new Task[MAX_TASKS_SIZE];
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
                "\n\t\t" + tasks[taskCount - 1] +
                "\n\t Now you have " + taskCount + " tasks in the list.";
    }

    private void addToDo(String commandArgs)
            throws NoDescriptionException {
        String description = commandArgs;
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        tasks[taskCount] = new ToDo(description);
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
        tasks[taskCount] = new Deadline(description, by);
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
        tasks[taskCount] = new Event(description, from, to);
    }

    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    public String getTasks() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskCount; i++) {
            message += "\n\t " + String.format("%d. ", i + 1) + tasks[i];
        }
        return message;
    }

    public String setIsDone(int taskIndex, boolean isDone) {
        try {
            tasks[taskIndex].setDone(isDone);
        } catch (Exception e) {
            return "Task " + (taskIndex + 1) + " does not exist";
        }
        if (isDone) {
            return "Nice! I've marked this task as done:\n\t\t " + tasks[taskIndex];
        } else {
            return "Nice! I've marked this task as not done yet:\n\t\t " + tasks[taskIndex];
        }
    }

    public String readTasksAsFile() {
        File f = new File(FILE_DIRECTORY + FILE_NAME); // create a File for the given file path
        Scanner s;
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (FileNotFoundException e) {
            return "Tasks file not found!";
        }
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        return "Tasks file read successfully";
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
            for (int i = 0; i < taskCount; i++){
                String textToAdd = tasks[i].getTaskAsText();
                fw.write(textToAdd + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            return "Tasks file failed to save";
        }
        return "Tasks file saved successfully" +
                "\n\tfull path: " + file.getAbsolutePath();
    }

}
