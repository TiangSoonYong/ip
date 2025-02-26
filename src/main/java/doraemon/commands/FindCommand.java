package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.TaskManager;
import doraemon.exceptions.InvalidKeywordException;

public class FindCommand extends Command {
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
