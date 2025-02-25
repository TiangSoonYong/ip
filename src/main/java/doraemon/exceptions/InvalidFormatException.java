package doraemon.exceptions;

public class InvalidFormatException extends AddTaskException{
    @Override
    public String getErrorMessage() {
        return "Date and time must follow YYYY-MM-DDtHH-MM-SS" +
                "\n\t and not exceed maximums";
    }
}
