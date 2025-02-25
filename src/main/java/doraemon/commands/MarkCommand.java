package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

public class MarkCommand extends Command {
    private final String taskNumber;
    private final boolean isDone;

    public MarkCommand(String taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws InvalidTaskNumberException {
        try {
            String feedback = taskManager.setIsDone(Integer.parseInt(this.taskNumber) - 1, this.isDone);
            formatter.echo(feedback);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
