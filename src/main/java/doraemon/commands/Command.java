package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.DoraemonException;
import doraemon.TaskManager;

import java.util.ArrayList;

public abstract class Command {
    protected static final String LINE_SEPARATOR = "\n\t "; // Line Separator
    public abstract void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws DoraemonException;
}
