package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;

public class ByeCommand extends Command{
    public static final String USAGE_INFO =
            "[bye]: Exits the programme" + LINE_SEPARATOR +
                    "Example: bye" + LINE_SEPARATOR;

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) {
        formatter.showGoodbye();
        System.exit(0);
    }
}
