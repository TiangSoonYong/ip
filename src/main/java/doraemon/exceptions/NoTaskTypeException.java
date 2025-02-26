package doraemon.exceptions;

public class NoTaskTypeException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "This is impossible, how did you get here!";
    }
}
