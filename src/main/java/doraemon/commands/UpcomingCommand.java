package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;
import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.InvalidTaskNumberException;

public class UpcomingCommand extends Command{
    public static final String USAGE_INFO =
            "[upcoming]: Display sorted list of tasks with date and time" + LINE_SEPARATOR +
                    "Example: upcoming" + LINE_SEPARATOR;
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.getUpcomingTasks();
        formatter.echo(feedback);
    }
}
