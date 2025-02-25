package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.task.TaskManager;

public class ClearCommand extends Command{
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.clearTasks();
        formatter.echo(feedback);
    }
}
