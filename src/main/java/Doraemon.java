import java.util.Scanner;


public class Doraemon {
    // Will go to Formatter class in future development
    private static final String LINE_PREFIX = "\t ";
    private static final String LINE_DIVIDER = "__________________________________________________";

    // Will go to Parser class in future development
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO = "Hello! I'm Doraemon!\n\t What can I do for you?";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

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
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
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
        default:
            // Invalid command should throw an exception but assume all valid for now
            return "Invalid Command";
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
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

