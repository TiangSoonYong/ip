package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

public class DeleteCommand extends Command {
    public static final String USAGE_INFO =
            "[delete]: Delete specified task" + LINE_SEPARATOR +
                    "Parameters: delete task-number" + LINE_SEPARATOR +
                    "Example: delete 1" + LINE_SEPARATOR;

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
