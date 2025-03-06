package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

public class MarkCommand extends Command {
    private static final String USAGE_INFO_MARK =
            "[mark]: Mark specified task as done" + LINE_SEPARATOR +
                    "Parameters: mark task-number" + LINE_SEPARATOR +
                    "Example: mark 1" + LINE_SEPARATOR;
    private static final String USAGE_INFO_UNMARK =
            "[unmark]: Mark specified task as not done" + LINE_SEPARATOR +
                    "Parameters: unmark task-number" + LINE_SEPARATOR +
                    "Example: unmark 1" + LINE_SEPARATOR;
    public static final String USAGE_INFO =
            USAGE_INFO_MARK + LINE_SEPARATOR +
                    USAGE_INFO_UNMARK;

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
