package doraemon;

public class Formatter {
    // Will go to Formatter class in future development
    // or perhaps a User Guide
    private static final String LINE_SEPARATOR = "\n\t "; // Line Separator
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO =
            "Hello! I'm Doraemon!" + LINE_SEPARATOR +
                    "What can I do for you?" + LINE_SEPARATOR +
                    "Type [help] for all commands";
    private static final String MESSAGE_INVALID =
            "Invalid Command!" + LINE_SEPARATOR
                    + "Type [help] for all commands";

    private static final String USAGE_INFO_TODO =
            "[todo]: Creates a task that you need to do" + LINE_SEPARATOR +
                    "Parameters: todo description" + LINE_SEPARATOR +
                    "Example: todo finish iP" + LINE_SEPARATOR;
    private static final String USAGE_INFO_DEADLINE =
            "[deadline]: Creates a task that needs to be finish by a deadline" + LINE_SEPARATOR +
                    "Parameters: deadline description /by end date" + LINE_SEPARATOR +
                    "Example: deadline do week 5 task /by 14 Feb 1600" + LINE_SEPARATOR;
    private static final String USAGE_INFO_EVENT =
            "[event]: Creates a task that have a start and end date" + LINE_SEPARATOR +
                    "Parameters: event description /from start date /to end date" + LINE_SEPARATOR +
                    "            event description /to end date /from start date" + LINE_SEPARATOR +
                    "Example: event exam week /from 26 Apr /to 10 May" + LINE_SEPARATOR;
    private static final String USAGE_INFO_LIST =
            "[list]: Display every tasks with task number, its type and whether it is done" + LINE_SEPARATOR +
                    "Format: [TaskType][isDone] Task_Description" + LINE_SEPARATOR +
                    "Example: list" + LINE_SEPARATOR;
    private static final String USAGE_INFO_MARK =
            "[mark]: Mark specified task as done" + LINE_SEPARATOR +
                    "Parameters: mark task-number" + LINE_SEPARATOR +
                    "Example: mark 1" + LINE_SEPARATOR;
    private static final String USAGE_INFO_UNMARK =
            "[unmark]: Mark specified task as not done" + LINE_SEPARATOR +
                    "Parameters: unmark task-number" + LINE_SEPARATOR +
                    "Example: unmark 1" + LINE_SEPARATOR;
    private static final String USAGE_INFO_DELETE =
            "[delete]: Delete specified task" + LINE_SEPARATOR +
                    "Parameters: delete task-number" + LINE_SEPARATOR +
                    "Example: delete 1" + LINE_SEPARATOR;
    private static final String USAGE_INFO_SAVE =
            "[save]: Save all tasks into a text file" + LINE_SEPARATOR +
                    "Example: save" + LINE_SEPARATOR;
    private static final String USAGE_INFO_CLEAR =
            "[clear]: Clear all tasks in the list" + LINE_SEPARATOR +
                    "Example: clear" + LINE_SEPARATOR;
    private static final String USAGE_INFO_HELP =
            "[help]: Displays this current message, showing all commands with examples and format" + LINE_SEPARATOR +
                    "Example: help" + LINE_SEPARATOR;
    private static final String USAGE_INFO_BYE =
            "[bye]: Exits the programme" + LINE_SEPARATOR +
                    "Example: bye" + LINE_SEPARATOR;
    private static final String MESSAGE_HELP =
            USAGE_INFO_TODO + LINE_SEPARATOR +
                    USAGE_INFO_DEADLINE + LINE_SEPARATOR +
                    USAGE_INFO_EVENT + LINE_SEPARATOR +
                    USAGE_INFO_LIST + LINE_SEPARATOR +
                    USAGE_INFO_MARK + LINE_SEPARATOR +
                    USAGE_INFO_UNMARK + LINE_SEPARATOR +
                    USAGE_INFO_DELETE + LINE_SEPARATOR +
                    USAGE_INFO_CLEAR + LINE_SEPARATOR +
                    USAGE_INFO_SAVE + LINE_SEPARATOR +
                    USAGE_INFO_HELP + LINE_SEPARATOR +
                    USAGE_INFO_BYE;

    // Will go to Formatter class in future development
    private static final String LINE_PREFIX = "\t ";
    private static final String LINE_DIVIDER = "__________________________________________________";

    // Formatter Methods
    public void echo(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    public void showLine() {
        System.out.println(LINE_PREFIX + LINE_DIVIDER);
    }

    public void showGreet() {
        this.showLine();
        this.echo(MESSAGE_HELLO);
        this.showLine();

    }

    public void showGoodbye() {
        this.echo(MESSAGE_GOODBYE);
    }

    public void showHelp() {
        this.echo(MESSAGE_HELP);
    }

    public void showInvalid() {
        this.echo(MESSAGE_INVALID);
    }
}
