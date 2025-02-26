package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class ListCommand extends Command {
    public static final String USAGE_INFO =
            "[list]: Display every tasks with task number, its type and whether it is done" + LINE_SEPARATOR +
                    "Format: [TaskType][isDone] Task_Description" + LINE_SEPARATOR +
                    "Example: list" + LINE_SEPARATOR;
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.getTasks();
        formatter.echo(feedback);
    }
}
