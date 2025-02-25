package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.task.TaskManager;
import doraemon.task.TaskType;

public class AddCommand extends Command {
    private final String commandArgs;
    private final TaskType taskType;

    public AddCommand(String commandArgs, TaskType taskType) {
        this.commandArgs = commandArgs;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        String feedback = taskManager.addTask(this.commandArgs, this.taskType);
        formatter.echo(feedback);
    }
}
