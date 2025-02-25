package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.task.TaskManager;
import doraemon.task.TaskType;

public class DeleteCommand extends Command {
    private final String commandArgs;

    public DeleteCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.deleteTask(Integer.parseInt(commandArgs) - 1);
        formatter.echo(feedback);
    }
}
