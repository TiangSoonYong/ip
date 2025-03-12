package doraemon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends DateTimeTask {
    protected static final TaskType type = TaskType.EVENT;
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, to, LocalDateTime.now().isAfter(to));
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
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + DELIMITER +
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private String formatForPrinting(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma"));
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (from: " + this.formatForPrinting(this.from) + " to: " + this.formatForPrinting(this.to) + ")";
    }
}
