package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;
import doraemon.exceptions.InvalidKeywordException;

public class FindCommand extends Command {
    public static final String USAGE_INFO =
            "[find]: Find tasks that matches specified keyword" + LINE_SEPARATOR +
                    "Parameters: find keyword" + LINE_SEPARATOR +
                    "Example: find book" + LINE_SEPARATOR;

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws InvalidKeywordException {
        String feedback = taskManager.findTasks(keyword);
        formatter.echo(feedback);
    }
}
