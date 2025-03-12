package doraemon.commands;

import doraemon.Formatter;
import doraemon.Storage;
import doraemon.exceptions.AddTaskException;
import doraemon.TaskManager;
import doraemon.task.TaskType;

public class AddCommand extends Command {
    private static final String USAGE_INFO_TODO =
            "[todo]: Creates a task that you need to do" + LINE_SEPARATOR +
                    "Parameters: todo description" + LINE_SEPARATOR +
                    "Example: todo finish iP" + LINE_SEPARATOR;
    private static final String USAGE_INFO_DEADLINE =
            "[deadline]: Creates a task that needs to be finish by a deadline" + LINE_SEPARATOR +
                    "Parameters: deadline description /by end date" + LINE_SEPARATOR +
                    "Format: yyyy-MM-dd HHmm" + LINE_SEPARATOR +
                    "Example: deadline do week 5 task /by 2025-02-14 1600" + LINE_SEPARATOR;
    private static final String USAGE_INFO_EVENT =
            "[event]: Creates a task that have a start and end date" + LINE_SEPARATOR +
                    "Parameters: event description /from start date /to end date" + LINE_SEPARATOR +
                    "            event description /to end date /from start date" + LINE_SEPARATOR +
                    "Format: yyyy-MM-dd HHmm" + LINE_SEPARATOR +
                    "Example: event exam week /from 2025-04-26 0800 /to 2025-05-10 2359" + LINE_SEPARATOR;
    public static final String USAGE_INFO =
            USAGE_INFO_TODO + LINE_SEPARATOR +
            USAGE_INFO_DEADLINE + LINE_SEPARATOR +
            USAGE_INFO_EVENT + LINE_SEPARATOR;

    private final String commandArgs;
    private final TaskType taskType;

    public AddCommand(String commandArgs, TaskType taskType) {
        this.commandArgs = commandArgs;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskManager taskManager, Formatter formatter, Storage storage) throws AddTaskException {
        String feedback = taskManager.addTask(this.commandArgs, this.taskType);
        formatter.echo(feedback);
    }
}
