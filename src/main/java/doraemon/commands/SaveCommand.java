package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class SaveCommand extends Command{
    public static final String USAGE_INFO =
            "[save]: Save all tasks into a text file" + LINE_SEPARATOR +
                    "Example: save" + LINE_SEPARATOR;

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.saveTasksIntoFile(storage);
        formatter.echo(feedback);
    }
}
