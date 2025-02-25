package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.task.TaskManager;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        formatter.showHelp();
    }
}
