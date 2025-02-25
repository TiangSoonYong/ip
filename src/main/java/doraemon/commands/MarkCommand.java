package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.task.TaskManager;

public class MarkCommand extends Command {
    private final String commandArgs;
    private final boolean isDone;

    public MarkCommand(String commandArgs, boolean isDone) {
        this.commandArgs = commandArgs;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws InvalidTaskNumberException {
        try {
            String feedback = taskManager.setIsDone(Integer.parseInt(this.commandArgs) - 1, this.isDone);
            formatter.echo(feedback);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
