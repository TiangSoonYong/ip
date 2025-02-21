package doraemon.exceptions;

public class NoByStringException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "The deadline is empty!";
    }
}
