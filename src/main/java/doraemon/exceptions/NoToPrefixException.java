package doraemon.exceptions;

public class NoToPrefixException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "To prefix [/to] is missing!";
    }
}
