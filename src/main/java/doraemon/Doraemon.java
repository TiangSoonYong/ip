package doraemon;

import doraemon.task.TaskManager;
import doraemon.task.TaskType;

import java.util.Scanner;


public class Doraemon {
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
    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        FORMATTER.showGreet();
        FORMATTER.echo(TASK_MANAGER.readTasksFromFile(STORAGE) + "\n\t " + TASK_MANAGER.getTasks());
        while (true) {
            String inputLine = SCANNER.nextLine();
            executeCommand(inputLine);
        }
    }

    // Parser Methods
    private static void executeCommand(String userInputString) {
        final String[] commandTypeAndParams = PARSER.splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0].toLowerCase();
        final String commandArgs = commandTypeAndParams[1];
        String feedback = "";
        try {
            switch (commandType) {
            case COMMAND_BYE:
                FORMATTER.showGoodbye();
                System.exit(0);
            case COMMAND_LIST:
                feedback = TASK_MANAGER.getTasks();
                break;
            case COMMAND_MARK:
                feedback = TASK_MANAGER.setIsDone(Integer.parseInt(commandArgs) - 1, true);
                break;
            case COMMAND_UNMARK:
                feedback = TASK_MANAGER.setIsDone(Integer.parseInt(commandArgs) - 1, false);
                break;
            case COMMAND_TODO:
                feedback = TASK_MANAGER.addTask(commandArgs, TaskType.TODO);
                break;
            case COMMAND_DEADLINE:
                feedback = TASK_MANAGER.addTask(commandArgs, TaskType.DEADLINE);
                break;
            case COMMAND_EVENT:
                feedback = TASK_MANAGER.addTask(commandArgs, TaskType.EVENT);
                break;
            case COMMAND_DELETE:
                feedback = TASK_MANAGER.deleteTask(Integer.parseInt(commandArgs) - 1);
                break;
            case COMMAND_HELP:
                FORMATTER.showHelp();
                break;
            case COMMAND_SAVE:
                feedback = TASK_MANAGER.saveTasksAsFile(STORAGE);
                break;
            case COMMAND_CLEAR:
                feedback = TASK_MANAGER.clearTasks();
                break;
            default:
                // In the future, can output all valid commands
                FORMATTER.showInvalid();
            }
        } catch (NumberFormatException e) {
            feedback = "Task number has to be an integer!";
        } finally {
            if (!feedback.isEmpty()) {
                FORMATTER.echo(feedback);
            }
        }
    }
}

