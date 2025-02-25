package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.AddTaskException;
import doraemon.exceptions.DoraemonException;
import doraemon.exceptions.InvalidKeywordException;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.TaskManager;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws DoraemonException;
}
