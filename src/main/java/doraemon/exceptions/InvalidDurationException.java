package doraemon.exceptions;

public class InvalidDurationException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "Start date must be earlier than end date";
    }
}
