package doraemon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Deadline extends DateTimeTask {
    protected static final TaskType type = TaskType.DEADLINE;
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, by, LocalDateTime.now().isAfter(by));
        this.by = by;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String getTaskAsText() {
        return this.getTaskIcon() + DELIMITER +
                this.getStatusIcon() + DELIMITER +
                this.description + DELIMITER +
                this.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private String formatForPrinting(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (by: " + this.formatForPrinting(this.by) + ")";
    }
}
