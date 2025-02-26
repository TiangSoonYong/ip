package doraemon;

import doraemon.commands.Command;
import doraemon.commands.ReadCommand;
import doraemon.exceptions.DoraemonException;

/**
 * <h1>Doraemon</h1>
 * Doraemon is a Personal Assistant Chatbot that helps you
 * keep track of various tasks, specifically to-do, deadline and event
 * Use <code>help</code> to view all available commands or
 * Check out the user guide in README.md
 *
 * @author Tiang Soon Yong
 * @version 1.1
 * @since 2025-02-26
 */

public class Doraemon {
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Storage STORAGE = new Storage();
    private static final Formatter FORMATTER = new Formatter();
    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        FORMATTER.showGreet();
        new ReadCommand().execute(TASK_MANAGER, FORMATTER, STORAGE);
        FORMATTER.showLine();
        while (true) {
            try {
                String inputLine = PARSER.readInput();
                FORMATTER.showLine();
                FORMATTER.echo("Command entered: " + inputLine);
                Command command = PARSER.getCommand(inputLine);
                command.execute(TASK_MANAGER, FORMATTER, STORAGE);
            } catch (DoraemonException e) {
                String errorMessage = e.getErrorMessage();
                FORMATTER.echo(errorMessage);
            } catch (Exception e) {
                // This is to catch and print any unhandled exceptions
                FORMATTER.echo(e.toString());
                System.exit(0);
            } finally {
                FORMATTER.showLine();
            }
        }
    }
}

