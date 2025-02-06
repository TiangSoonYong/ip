public class TaskManager {
    private static final String DATA_PREFIX_BY = "/by";
    private static final String DATA_PREFIX_FROM = "/from";
    private static final String DATA_PREFIX_TO = "/to";

    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    public String addTask(String commandArgs, TaskType taskType) {
        switch (taskType) {
        case TODO:
            addToDo(commandArgs);
            break;
        case DEADLINE:
            addDeadline(commandArgs);
            break;
        case EVENT:
            addEvent(commandArgs);
            break;
        default:
            // Throw exception
        }
        taskCount++;
        return "Got it. I've added this task:" +
                "\n\t\t" + tasks[taskCount - 1] +
                "\n\t Now you have " + taskCount + " tasks in the list.";
    }

    private void addToDo(String commandArgs) {
        String description = commandArgs;
        tasks[taskCount] = new ToDo(description);
    }

    private void addDeadline(String commandArgs) {
        final int indexOfByPrefix = commandArgs.indexOf(DATA_PREFIX_BY);
        String description = commandArgs.substring(0, indexOfByPrefix).trim();
        String by = removePrefixSign(commandArgs.substring(indexOfByPrefix, commandArgs.length()), DATA_PREFIX_BY).trim();
        tasks[taskCount] = new Deadline(description, by);
    }

    private void addEvent(String commandArgs) {
        final int indexOfFromPrefix = commandArgs.indexOf(DATA_PREFIX_FROM);
        final int indexOfToPrefix = commandArgs.indexOf(DATA_PREFIX_TO);
        int indexOfFirstPrefix = Math.min(indexOfFromPrefix, indexOfToPrefix);
        String description;
        String from;
        String to;

        description = commandArgs.substring(0, indexOfFirstPrefix).trim();
        if (indexOfFromPrefix < indexOfToPrefix) { // Description - From - To
            from = removePrefixSign(
                    commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                    DATA_PREFIX_FROM).trim();
            to = removePrefixSign(commandArgs.substring(indexOfToPrefix, commandArgs.length()),
                    DATA_PREFIX_TO).trim();

        } else { // Description - To - From
            from = removePrefixSign(commandArgs.substring(indexOfFromPrefix, commandArgs.length()),
                    DATA_PREFIX_FROM).trim();
            to = removePrefixSign(commandArgs.substring(indexOfToPrefix, indexOfFromPrefix),
                    DATA_PREFIX_TO).trim();
        }
        tasks[taskCount] = new Event(description, from, to);
    }

    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    public String getTasks() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskCount; i++) {
            message += "\n\t " + String.format("%d. ", i + 1) + tasks[i];
        }
        return message;
    }

    public String setIsDone(int taskIndex, boolean isDone) {
        tasks[taskIndex].setDone(isDone);
        if (isDone) {
            return "Nice! I've marked this task as done:\n\t\t " + tasks[taskIndex];
        } else {
            return "Nice! I've marked this task as not done yet:\n\t\t " + tasks[taskIndex];
        }
    }
}
