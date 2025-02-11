import java.util.Scanner;


public class Doraemon {
    // Will go to Formatter class in future development
    private static final String LINE_PREFIX = "\t ";
    private static final String LINE_DIVIDER = "__________________________________________________";

    // Will go to Parser class in future development
    private static final String LS = "\n\t "; // Line Separator
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO =
            "Hello! I'm Doraemon!" + LS +
                    "What can I do for you?" + LS +
                    "Type [help] for all commands";
    private static final String MESSAGE_INVALID =
            "Invalid Command!" + LS
                    + "Type [help] for all commands";
    // Split into individual message, allowing them to be used for exceptions handling
    private static final String MESSAGE_HELP =
            "[todo]: Creates a task that you need to do" + LS +
                    "Parameters: todo description" + LS +
                    "Example: todo finish iP" + LS + LS +
                    "[deadline]: Creates a task that needs to be finish by a deadline" + LS +
                    "Parameters: deadline description /by end date" + LS +
                    "Example: deadline do week 5 task /by 14 Feb 1600" + LS + LS +
                    "[event]: Creates a task that have a start and end date" + LS +
                    "Parameters: event description /from start date /to end date" + LS +
                    "            event description /to end date /from start date" + LS +
                    "Example: event exam week /from 26 Apr /to 10 May" + LS + LS +
                    "[list]: Display every tasks with task number, its type and whether it is done" + LS +
                    "Format: [TaskType][isDone] Task_Description" + LS +
                    "Example: list" + LS + LS +
                    "[mark]: Mark specified task as done" + LS +
                    "Parameters: mark task-number" + LS +
                    "Example: mark 1" + LS + LS +
                    "[unmark]: Mark specified task as not done" + LS +
                    "Parameters: unmark task-number" + LS +
                    "Example: unmark 1" + LS + LS +
                    "[help]: Displays this current message, showing all commands with examples and format" + LS +
                    "Example: help" + LS + LS +
                    "[bye]: Exits the programme" + LS +
                    "Example: bye";


    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_HELP = "help";

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        echo(MESSAGE_HELLO);
        while (true) {
            String inputLine = SCANNER.nextLine();
            String feedback = executeCommand(inputLine);
            echo(feedback);
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
                echo(MESSAGE_GOODBYE);
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
            case COMMAND_HELP:
                return MESSAGE_HELP;
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

    // Formatter Methods
    private static void echo(String... message) {
        System.out.println(LINE_PREFIX + LINE_DIVIDER);
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
        System.out.println(LINE_PREFIX + LINE_DIVIDER);
    }
}

