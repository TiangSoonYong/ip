package doraemon.exceptions;

public class InvalidTaskNumberException extends DoraemonException {

    @Override
    public String getErrorMessage() {
        return "Task number must be an integer!";
    }
}
