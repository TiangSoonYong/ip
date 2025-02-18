package doraemon.task;

public class Event extends Task {
    protected static final TaskType type = TaskType.EVENT;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
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
                this.from + DELIMITER +
                this.to;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
