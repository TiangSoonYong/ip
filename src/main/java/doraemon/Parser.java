package doraemon;

import doraemon.commands.AddCommand;
import doraemon.commands.ByeCommand;
import doraemon.commands.ClearCommand;
import doraemon.commands.Command;
import doraemon.commands.DefaultCommand;
import doraemon.commands.DeleteCommand;
import doraemon.commands.FindCommand;
import doraemon.commands.HelpCommand;
import doraemon.commands.ListCommand;
import doraemon.commands.MarkCommand;
import doraemon.commands.SaveCommand;
import doraemon.commands.UpcomingCommand;
import doraemon.exceptions.InvalidTaskNumberException;
import doraemon.task.TaskType;

import java.util.Scanner;

/**
 * <h1>Parser</h1>
 * Parser deals with making sense of the user command
 */

public class Parser {
    /**
     * Command List
     */
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_UPCOMING = "upcoming";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Splits and returns command words and arguments
     *
     * @param rawUserInput
     * @return splitted string
     */
    private String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }

    public String readInput() {
        String inputLine = SCANNER.nextLine();
        return inputLine;
    }

    /**
     * Returns command according to user input
     *
     * @param userInputString
     * @return Command
     */

    public Command getCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0].toLowerCase();
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_BYE:
            return new ByeCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return new MarkCommand(commandArgs, true);
        case COMMAND_UNMARK:
            return new MarkCommand(commandArgs, false);
        case COMMAND_TODO:
            return new AddCommand(commandArgs, TaskType.TODO);
        case COMMAND_DEADLINE:
            return new AddCommand(commandArgs, TaskType.DEADLINE);
        case COMMAND_EVENT:
            return new AddCommand(commandArgs, TaskType.EVENT);
        case COMMAND_DELETE:
            return new DeleteCommand(commandArgs);
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_SAVE:
            return new SaveCommand();
        case COMMAND_CLEAR:
            return new ClearCommand();
        case COMMAND_FIND:
            return new FindCommand(commandArgs);
        case COMMAND_UPCOMING:
            return new UpcomingCommand();
        default:
            return new DefaultCommand();
        }
    }
}
