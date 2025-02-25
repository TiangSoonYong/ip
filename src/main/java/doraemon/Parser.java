package doraemon;

public class Parser {

    public String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        if (split.length == 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }
}
