package doraemon.exceptions;

public class InvalidKeywordException extends DoraemonException{
    @Override
    public String getErrorMessage() {
        return "Invalid keyword";
    }
}
