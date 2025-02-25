package doraemon;

import doraemon.commands.Command;
import doraemon.task.TaskManager;



public class Doraemon {
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Storage STORAGE = new Storage();
    private static final Formatter FORMATTER = new Formatter();
    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        FORMATTER.showGreet();
        FORMATTER.echo(TASK_MANAGER.readTasksFromFile(STORAGE) + "\n\t " + TASK_MANAGER.getTasks());
        while (true) {
            try {
                String inputLine = PARSER.readInput();
                Command command = PARSER.getCommand(inputLine);
                command.execute(TASK_MANAGER, FORMATTER, STORAGE);
            } catch (Exception e) {
                /* Exceptions are handled within individual classes
                 * This is to catch and print any unhandled exceptions
                 */
                FORMATTER.echo(e.getLocalizedMessage());
                System.exit(0);
            }
        }
    }
}

