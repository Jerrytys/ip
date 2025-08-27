package betty.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + super.toString();
    }

    @Override
    public String toSaveString() {
        String doneValue = super.isDone() ? "1" : "0";
        return "T | " + super.toSaveString();
    }
}
