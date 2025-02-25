package doraemon;

import doraemon.task.Deadline;
import doraemon.task.Event;
import doraemon.task.Task;
import doraemon.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMITER = " \\| ";
    private static final String DONE_ICON = "X";

    public String readTasksFromFile(ArrayList<Task> tasks) {
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
            message += saveTasksAsFile(tasks) + "\n\t ";
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

    public String saveTasksAsFile(ArrayList<Task> tasks) {
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
