package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.task.TaskManager;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws AddTaskException, InvalidTaskNumberException;
}
