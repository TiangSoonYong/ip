package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

public class DeleteCommand extends Command {
    private final String commandArgs;

    public DeleteCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws InvalidTaskNumberException {
        try {
            String feedback = taskManager.deleteTask(Integer.parseInt(commandArgs) - 1);
            formatter.echo(feedback);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
