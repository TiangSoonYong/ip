package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class ClearCommand extends Command{
    public static final String USAGE_INFO =
            "[clear]: Clear all tasks in the list" + LINE_SEPARATOR +
                    "Example: clear" + LINE_SEPARATOR;

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.clearTasks();
        formatter.echo(feedback);
    }
}
