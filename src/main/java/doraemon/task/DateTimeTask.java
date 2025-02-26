package doraemon.task;

import java.time.LocalDateTime;

public abstract class DateTimeTask extends Task {
    private final LocalDateTime keyDateTime;
    private final boolean isOverdue;

    public DateTimeTask(String description, LocalDateTime keyDateTime, boolean isOverdue) {
        super(description);
        this.keyDateTime = keyDateTime;
        this.isOverdue = isOverdue;
    }

    @Override
    public boolean hasDateTime() {
        return true;
    }

    public LocalDateTime getKeyDateTime() {
        return this.keyDateTime;
    }

    @Override
    public String toString() {
        if (this.isOverdue) {
            return super.toString() + "(OVERDUE)";
        } else {
            return super.toString();
        }
    }
}
