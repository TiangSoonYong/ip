package doraemon.exceptions;

public class NoByPrefixException extends AddTaskException {

    @Override
    public String getErrorMessage() {
        return "By prefix [/by] is missing!";
    }
}
