package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class DefaultCommand extends Command{
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        formatter.showInvalid();
    }
}
