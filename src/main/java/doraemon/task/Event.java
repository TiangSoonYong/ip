package doraemon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected static final TaskType type = TaskType.EVENT;
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String getTaskAsText() {
        return this.getTaskIcon() + DELIMITER +
                this.getStatusIcon() + DELIMITER +
                this.description + DELIMITER +
                this.from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + DELIMITER +
                this.to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private String formatForPrinting(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (from: " + this.formatForPrinting(this.from) + " to: " + this.formatForPrinting(this.to) + ")";
    }
}
