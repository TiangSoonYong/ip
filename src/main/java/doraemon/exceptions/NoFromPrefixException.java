package doraemon.exceptions;

public class NoFromPrefixException extends AddTaskException {
    @Override
    public String getErrorMessage() {
        return "From prefix [/from] is missing!";
    }
}
