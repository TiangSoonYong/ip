package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class ReadCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.readTasksFromFile(storage) + "\n\t " + taskManager.getTasks();
        formatter.echo(feedback);
    }
}
