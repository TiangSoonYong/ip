package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.task.TaskManager;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Formatter formatter, Storage storage);
}
