package doraemon;

public class Formatter {
    // Will go to Formatter class in future development
    private static final String LINE_PREFIX = "\t ";
    private static final String LINE_DIVIDER = "__________________________________________________";

    // Formatter Methods
    public void echo(String... message) {
        System.out.println(LINE_PREFIX + LINE_DIVIDER);
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
        System.out.println(LINE_PREFIX + LINE_DIVIDER);
    }
}
