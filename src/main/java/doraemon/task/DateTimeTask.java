package doraemon.task;

import java.time.LocalDateTime;

public abstract class DateTimeTask extends Task {
    private final LocalDateTime keyDateTime;

    public DateTimeTask(String description, LocalDateTime keyDateTime) {
        super(description);
        this.keyDateTime = keyDateTime;
    }

    @Override
    public boolean hasDateTime() {
        return true;
    }

    public LocalDateTime getKeyDateTime() {
        return this.keyDateTime;
    }
}
