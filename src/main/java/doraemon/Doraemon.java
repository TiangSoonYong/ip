package doraemon;

import doraemon.commands.Command;
import doraemon.commands.ReadCommand;
import doraemon.exceptions.DoraemonException;
import doraemon.task.TaskManager;



public class Doraemon {
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Storage STORAGE = new Storage();
    private static final Formatter FORMATTER = new Formatter();
    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        FORMATTER.showGreet();
        new ReadCommand().execute(TASK_MANAGER, FORMATTER, STORAGE);
        while (true) {
            try {
                String inputLine = PARSER.readInput();
                Command command = PARSER.getCommand(inputLine);
                command.execute(TASK_MANAGER, FORMATTER, STORAGE);
            } catch (DoraemonException e){
                String errorMessage = e.getErrorMessage();
                FORMATTER.echo(errorMessage);
            } catch (Exception e) {
                // This is to catch and print any unhandled exceptions
                FORMATTER.echo(e.toString());
                System.exit(0);
            }
        }
    }
}

