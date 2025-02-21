package doraemon.exceptions;

public class NoFromStringException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "The start date is missing!";
    }
}
