package doraemon;

import doraemon.exceptions.NoTaskTypeException;
import doraemon.task.Deadline;
import doraemon.task.Event;
import doraemon.task.Task;
import doraemon.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>Storage</h1>
 * Storage deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMITER = " \\| ";
    private static final String DONE_ICON = "X";

    /**
     * Load tasks from file specified by filepath
     * Return error message if text file cannot be found
     * Ignores corrupted texts
     *
     * @param tasks
     * @return message
     */
    public String loadTasksFromFile(ArrayList<Task> tasks) {
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
            message += saveTasksIntoFile(tasks) + "\n\t ";
        }
        return message + "Tasks.txt read successfully";
    }

    /**
     * Returns task according to arguments specified in the text
     *
     * @param commandArgs
     * @return Task
     * @throws NoTaskTypeException
     */

    private static Task parseTasksFromText(String[] commandArgs) throws NoTaskTypeException {
        Task temp;
        String taskType = commandArgs[0];
        String taskStatus = commandArgs[1];
        String description = commandArgs[2];
        switch (taskType) {
        case "T":
            temp = new ToDo(description);
            break;
        case "D":
            String byString = commandArgs[3];
            LocalDateTime by = LocalDateTime.parse(byString);
            temp = new Deadline(description, by);
            break;
        case "E":
            String fromString = commandArgs[3];
            String toString = commandArgs[4];
            LocalDateTime from = LocalDateTime.parse(fromString);
            LocalDateTime to = LocalDateTime.parse(toString);
            temp = new Event(description, from, to);
            break;
        default:
            throw new NoTaskTypeException();
        }
        temp.setDone(taskStatus.equals(DONE_ICON));
        return temp;
    }

    /**
     * Saves tasks from tasks list into text file
     * Creates directory and file if they do not exist
     * Returns error message if save was unsuccessful
     *
     * @param tasks
     * @return message
     */

    public String saveTasksIntoFile(ArrayList<Task> tasks) {
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
