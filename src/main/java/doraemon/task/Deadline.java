package doraemon.task;

public class Deadline extends Task {
    protected static final TaskType type = TaskType.DEADLINE;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
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
                this.by;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " (by: " + this.by + ")";
    }
}
