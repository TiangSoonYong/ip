package doraemon;

import doraemon.task.TaskManager;
import doraemon.task.TaskType;

import java.util.Scanner;


public class Doraemon {
    // Will go to Formatter class in future development
    // or perhaps a User Guide
    private static final String LINE_SEPERATOR = "\n\t "; // Line Separator
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO =
            "Hello! I'm Doraemon!" + LINE_SEPERATOR +
                    "What can I do for you?" + LINE_SEPERATOR +
                    "Type [help] for all commands";
    private static final String MESSAGE_INVALID =
            "Invalid Command!" + LINE_SEPERATOR
                    + "Type [help] for all commands";

    private static final String USAGE_INFO_TODO =
            "[todo]: Creates a task that you need to do" + LINE_SEPERATOR +
                    "Parameters: todo description" + LINE_SEPERATOR +
                    "Example: todo finish iP" + LINE_SEPERATOR;
    private static final String USAGE_INFO_DEADLINE =
            "[deadline]: Creates a task that needs to be finish by a deadline" + LINE_SEPERATOR +
                    "Parameters: deadline description /by end date" + LINE_SEPERATOR +
                    "Example: deadline do week 5 task /by 14 Feb 1600" + LINE_SEPERATOR;
    private static final String USAGE_INFO_EVENT =
            "[event]: Creates a task that have a start and end date" + LINE_SEPERATOR +
                    "Parameters: event description /from start date /to end date" + LINE_SEPERATOR +
                    "            event description /to end date /from start date" + LINE_SEPERATOR +
                    "Example: event exam week /from 26 Apr /to 10 May" + LINE_SEPERATOR;
    private static final String USAGE_INFO_LIST =
            "[list]: Display every tasks with task number, its type and whether it is done" + LINE_SEPERATOR +
                    "Format: [TaskType][isDone] Task_Description" + LINE_SEPERATOR +
                    "Example: list" + LINE_SEPERATOR;
    private static final String USAGE_INFO_MARK =
            "[mark]: Mark specified task as done" + LINE_SEPERATOR +
                    "Parameters: mark task-number" + LINE_SEPERATOR +
                    "Example: mark 1" + LINE_SEPERATOR;
    private static final String USAGE_INFO_UNMARK =
            "[unmark]: Mark specified task as not done" + LINE_SEPERATOR +
                    "Parameters: unmark task-number" + LINE_SEPERATOR +
                    "Example: unmark 1" + LINE_SEPERATOR;
    private static final String USAGE_INFO_DELETE =
            "[delete]: Delete specified task" + LINE_SEPERATOR +
                    "Parameters: delete task-number" + LINE_SEPERATOR +
                    "Example: delete 1" + LINE_SEPERATOR;
    private static final String USAGE_INFO_SAVE =
            "[save]: Save all tasks into a text file" + LINE_SEPERATOR +
                    "Example: save" + LINE_SEPERATOR;
    private static final String USAGE_INFO_CLEAR =
            "[clear]: Clear all tasks in the list" + LINE_SEPERATOR +
                    "Example: clear" + LINE_SEPERATOR;
    private static final String USAGE_INFO_HELP =
            "[help]: Displays this current message, showing all commands with examples and format" + LINE_SEPERATOR +
                    "Example: help" + LINE_SEPERATOR;
    private static final String USAGE_INFO_BYE =
            "[bye]: Exits the programme" + LINE_SEPERATOR +
                    "Example: bye" + LINE_SEPERATOR;
    private static final String MESSAGE_HELP =
            USAGE_INFO_TODO + LINE_SEPERATOR +
                    USAGE_INFO_DEADLINE + LINE_SEPERATOR +
                    USAGE_INFO_EVENT + LINE_SEPERATOR +
                    USAGE_INFO_LIST + LINE_SEPERATOR +
                    USAGE_INFO_MARK + LINE_SEPERATOR +
                    USAGE_INFO_UNMARK + LINE_SEPERATOR +
                    USAGE_INFO_DELETE + LINE_SEPERATOR +
                    USAGE_INFO_CLEAR + LINE_SEPERATOR +
                    USAGE_INFO_SAVE + LINE_SEPERATOR +
                    USAGE_INFO_HELP + LINE_SEPERATOR +
                    USAGE_INFO_BYE;


    // Will go into Parser class in future development
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_CLEAR = "clear";

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Storage STORAGE = new Storage();
    private static final Formatter FORMATTER = new Formatter();

    public static void main(String[] args) {
        FORMATTER.echo(MESSAGE_HELLO);
        FORMATTER.echo(TASK_MANAGER.readTasksFromFile(STORAGE) + LINE_SEPERATOR + TASK_MANAGER.getTasks());
        while (true) {
            String inputLine = SCANNER.nextLine();
            String feedback = executeCommand(inputLine);
            FORMATTER.echo(feedback);
        }
    }

    // Parser Methods
    private static String executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0].toLowerCase();
        final String commandArgs = commandTypeAndParams[1];
        try {
            switch (commandType) {
            case COMMAND_BYE:
                FORMATTER.echo(MESSAGE_GOODBYE);
                System.exit(0);
            case COMMAND_LIST:
                return TASK_MANAGER.getTasks();
            case COMMAND_MARK:
                return TASK_MANAGER.setIsDone(Integer.parseInt(commandArgs) - 1, true);
            case COMMAND_UNMARK:
                return TASK_MANAGER.setIsDone(Integer.parseInt(commandArgs) - 1, false);
            case COMMAND_TODO:
                return TASK_MANAGER.addTask(commandArgs, TaskType.TODO);
            case COMMAND_DEADLINE:
                return TASK_MANAGER.addTask(commandArgs, TaskType.DEADLINE);
            case COMMAND_EVENT:
                return TASK_MANAGER.addTask(commandArgs, TaskType.EVENT);
            case COMMAND_DELETE:
                return TASK_MANAGER.deleteTask(Integer.parseInt(commandArgs) - 1);
            case COMMAND_HELP:
                return MESSAGE_HELP;
            case COMMAND_SAVE:
                return TASK_MANAGER.saveTasksAsFile(STORAGE);
            case COMMAND_CLEAR:
                return TASK_MANAGER.clearTasks();
            default:
                // In the future, can output all valid commands
                return MESSAGE_INVALID;
            }
        } catch (NumberFormatException e) {
            return "Task number has to be an integer!";
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }
}

