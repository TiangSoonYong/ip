package doraemon.task;

public class ToDo extends Task {
    protected static final TaskType type = TaskType.TODO;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String getTaskAsText() {
        return this.getTaskIcon() + DELIMITER +
                this.getStatusIcon() + DELIMITER +
                this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString();
    }
}
