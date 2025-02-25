package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.DoraemonException;
import doraemon.TaskManager;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws DoraemonException;
}
