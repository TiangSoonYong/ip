package doraemon.exceptions;

public class NoDescriptionException extends AddTaskException {
    @Override
    public String getErrorMessage() {
        return "The description is empty!";
    }
}
