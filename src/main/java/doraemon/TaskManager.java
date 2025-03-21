package doraemon;

import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.InvalidDurationException;
import doraemon.exceptions.InvalidFormatException;
import doraemon.exceptions.InvalidKeywordException;
import doraemon.exceptions.NoByPrefixException;
import doraemon.exceptions.NoByStringException;
import doraemon.exceptions.NoDescriptionException;
import doraemon.exceptions.NoFromPrefixException;
import doraemon.exceptions.NoFromStringException;
import doraemon.exceptions.NoTaskTypeException;
import doraemon.exceptions.NoToPrefixException;
import doraemon.exceptions.NoToStringException;
import doraemon.task.DateTimeTask;
import doraemon.task.Deadline;
import doraemon.task.Event;
import doraemon.task.Task;
import doraemon.task.TaskType;
import doraemon.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>TaskManager</h1>
 * TaskManager contains the task list and every task operations
 */

public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Calls the respective TaskType add methods according to
     * the command specification
     *
     * @param commandArgs
     * @param taskType
     * @return feedback
     * @throws NoTaskTypeException If no TaskType was given
     */

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
            throw new NoTaskTypeException();
        }
        return "Got it. I've added this task:" +
                "\n\t\t" + tasks.get(tasks.size() - 1) +
                "\n\t Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds todo task into the task list
     *
     * @param commandArgs
     * @throws NoDescriptionException If no description was given
     */

    private void addToDo(String commandArgs) throws AddTaskException {
        String description = commandArgs;
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        tasks.add(tasks.size(), new ToDo(description));
    }

    /**
     * Adds deadline task into the task list
     * Removes the prefix from the command arguments
     *
     * @param commandArgs
     * @throws NoDescriptionException If no description was given
     * @throws NoByPrefixException If "/by" prefix was not found
     * @throws NoByStringException If no deadline was given
     * <p>
     * Note: Add different format in future development
     * 1. Allow only LocalDate or LocalTime
     * Example 1: /by 2025-02-25
     * Example 2: /by 14:00
     * <p>
     * 2. Automatically append current year if year not given
     * Example: /by 02-25
     * <p>
     * 3. Allow to add duration instead of specifying deadline
     * Example: /by 3 weeks
     */

    private void addDeadline(String commandArgs) throws AddTaskException {
        final int indexOfByPrefix = commandArgs.indexOf(DATA_PREFIX_BY);
        if (indexOfByPrefix < 0) {
            throw new NoByPrefixException();
        }
        String description = commandArgs.substring(0, indexOfByPrefix).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }

        String byString = removePrefixSign(commandArgs.substring(indexOfByPrefix, commandArgs.length()), DATA_PREFIX_BY).trim();
        LocalDateTime by;
        if (byString.isEmpty()) {
            throw new NoByStringException();
        }
        try {
            by = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException();
        }
        tasks.add(tasks.size(), new Deadline(description, by));
    }

    /**
     * Adds event task into the task list
     * Detects which prefixes came first
     * Removes the prefixes from the command arguments
     *
     * @param commandArgs
     * @throws NoDescriptionException If no description was given
     * @throws NoFromPrefixException If "/from" prefix was not found
     * @throws NoFromStringException If no start date was given
     * @throws NoToPrefixException If "/to" prefix was not found
     * @throws NoToStringException If no end date was given
     * @throws InvalidDurationException If start date is later than end date
     * <p>
     * Note: Add different format in future development
     * 1. Allow only LocalDate or LocalTime
     * Example 1: /from 2025-02-25 /to 2025-02-26
     * Example 2: /from 14:00 /to 16:00
     * <p>
     * 2. Automatically detect the same date if date not given
     * Example: /from 2025-02-25t16:00 /to 18:00
     * <p>
     * 3. Automatically append current year if year not given
     * Example: /from 02-25 /to 03-25
     * <p>
     * 4. Allow to add duration instead of specifying end date
     * Example: /from 2025-02-25t16:00 /duration 6 months
     */

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
        String fromString;
        String toString;
        LocalDateTime from;
        LocalDateTime to;

        description = commandArgs.substring(0, indexOfFirstPrefix).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }
        if (indexOfFromPrefix < indexOfToPrefix) { // Description - From - To
            fromString = removePrefixSign(commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                    DATA_PREFIX_FROM).trim();
            toString = removePrefixSign(commandArgs.substring(indexOfToPrefix, commandArgs.length()),
                    DATA_PREFIX_TO).trim();

        } else { // Description - To - From
            fromString = removePrefixSign(commandArgs.substring(indexOfFromPrefix, commandArgs.length()),
                    DATA_PREFIX_FROM).trim();
            toString = removePrefixSign(commandArgs.substring(indexOfToPrefix, indexOfFromPrefix),
                    DATA_PREFIX_TO).trim();
        }

        if (fromString.isEmpty()) {
            throw new NoFromStringException();
        }
        if (toString.isEmpty()) {
            throw new NoToStringException();
        }

        try {
            from = LocalDateTime.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            to = LocalDateTime.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException();
        }

        if(from.isAfter(to)) {
            throw new InvalidDurationException();
        }

        tasks.add(tasks.size(), new Event(description, from, to));
    }

    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    /**
     * Returns message containing every task in the list
     *
     * @return message
     */

    public String getTasks() {
        if (tasks.isEmpty()) {
            return "You do not have any tasks in your list";
        }
        String message = "Here are the tasks in your list:";
        for (Task task : tasks) {
            message += "\n\t " + String.format("%d. ", tasks.indexOf(task) + 1) + "\t" + task;
        }
        return message;
    }

    /**
     * Returns message containing every task with dates in the list
     * Sorted the tasks from earliest to latest
     *
     * @return message
     */
    public String getUpcomingTasks() {
        String message = "Here are the upcoming tasks in your list:";
        List<DateTimeTask> dateTimeTaskList = tasks.stream().filter(Task::hasDateTime).map(task -> (DateTimeTask) task).toList();
        List<DateTimeTask> sortedDateTimeTaskList = dateTimeTaskList.stream().sorted(Comparator.comparing(DateTimeTask::getKeyDateTime)).toList();
        for (DateTimeTask dateTimeTask : sortedDateTimeTaskList) {
            message += "\n\t " + String.format("%d. ", tasks.indexOf(dateTimeTask) + 1) + "\t" + dateTimeTask;
        }
        return message;
    }

    /**
     * Mark or unmark selected tasks
     * If taskIndex does not exist, error message is returned
     *
     * @param taskIndex
     * @param isDone
     * @return message
     */
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

    /**
     * Deletes selected task
     * If taskIndex does not exist, error message is returned
     *
     * @param taskIndex
     * @return message
     */

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

    /**
     * Returns message containing every task that matches the keyword
     *
     * @param keyword
     * @return message
     * @throws InvalidKeywordException
     */

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
  
    /**
     * Clears the task list
     *
     * @return message
     */

    public String clearTasks() {
        tasks.clear();
        return "All tasks has been cleared";
    }

    /**
     * Calls storage object to read tasks from file
     *
     * @param storage
     * @return message
     */

    public String loadTasksFromFile(Storage storage) {
        return storage.loadTasksFromFile(tasks);
    }

    /**
     * Calls storage object to save tasks into file
     *
     * @param storage
     * @return message
     */

    public String saveTasksIntoFile(Storage storage) {
        return storage.saveTasksIntoFile(tasks);
    }
}
