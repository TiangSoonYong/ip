package doraemon.exceptions;

public class InvalidFormatException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "Date and time must follow yyyy-MM-dd HHmm" +
                "\n\t and not exceed maximums";
    }
}
