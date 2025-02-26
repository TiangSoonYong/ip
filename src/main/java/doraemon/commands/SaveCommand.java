package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class SaveCommand extends Command{
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.saveTasksIntoFile(storage);
        formatter.echo(feedback);
    }
}
