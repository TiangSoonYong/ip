package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

public class DeleteCommand extends Command {
    private final String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws InvalidTaskNumberException {
        try {
            String feedback = taskManager.deleteTask(Integer.parseInt(taskNumber) - 1);
            formatter.echo(feedback);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
