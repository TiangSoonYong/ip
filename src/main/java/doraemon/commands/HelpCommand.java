package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class HelpCommand extends Command{
    public static final String USAGE_INFO =
            "[help]: Displays this current message, showing all commands with examples and format" + LINE_SEPARATOR +
                    "Example: help" + LINE_SEPARATOR;

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        formatter.showHelp();
    }
}
