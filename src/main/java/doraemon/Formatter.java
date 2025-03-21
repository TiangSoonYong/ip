package doraemon;

import doraemon.commands.AddCommand;
import doraemon.commands.ByeCommand;
import doraemon.commands.ClearCommand;
import doraemon.commands.DeleteCommand;
import doraemon.commands.FindCommand;
import doraemon.commands.HelpCommand;
import doraemon.commands.ListCommand;
import doraemon.commands.MarkCommand;
import doraemon.commands.SaveCommand;
import doraemon.commands.UpcomingCommand;

/**
 * <h1>Formatter</h1>
 * Formatter deals with interactions with the user
 */
public class Formatter {
    private static final String LINE_SEPARATOR = "\n\t "; // Line Separator
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO =
            "Hello! I'm Doraemon!" + LINE_SEPARATOR +
                    "What can I do for you?" + LINE_SEPARATOR +
                    "Check out the User Guide on https://tiangsoonyong.github.io/ip/" + LINE_SEPARATOR +
                    "Type [help] for all commands" + LINE_SEPARATOR +
                    "Note to User:" + LINE_SEPARATOR +
                    "- The current version follows STRICTLY to Date-Time Format: yyyy-MM-dd HHmm" + LINE_SEPARATOR +
                    "  Example: 2025-03-07 0240 translates to 07 Mar 2025, 02:40am" + LINE_SEPARATOR +
                    "  Future versions will accept flexible formatting" + LINE_SEPARATOR +
                    "- Files are not saved automatically! Use the save command frequently!";
    private static final String MESSAGE_INVALID =
            "Invalid Command!" + LINE_SEPARATOR
                    + "Type [help] for all commands";

    private static final String MESSAGE_HELP =
            AddCommand.USAGE_INFO + LINE_SEPARATOR +
                    ListCommand.USAGE_INFO + LINE_SEPARATOR +
                    UpcomingCommand.USAGE_INFO + LINE_SEPARATOR +
                    MarkCommand.USAGE_INFO + LINE_SEPARATOR +
                    DeleteCommand.USAGE_INFO + LINE_SEPARATOR +
                    FindCommand.USAGE_INFO + LINE_SEPARATOR +
                    ClearCommand.USAGE_INFO + LINE_SEPARATOR +
                    SaveCommand.USAGE_INFO + LINE_SEPARATOR +
                    HelpCommand.USAGE_INFO + LINE_SEPARATOR +
                    ByeCommand.USAGE_INFO;

    private static final String LINE_PREFIX = "\t ";
    private static final String LINE_DIVIDER = "__________________________________________________";

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
        this.showLine();
    }

    public void showHelp() {
        this.echo(MESSAGE_HELP);
    }

    public void showInvalid() {
        this.echo(MESSAGE_INVALID);
    }
}
