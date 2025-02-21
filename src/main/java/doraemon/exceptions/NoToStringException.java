package doraemon.exceptions;

public class NoToStringException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "The end date is missing!";
    }
}
