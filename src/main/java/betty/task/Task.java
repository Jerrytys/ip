package betty.task;

public class Task {

    protected boolean isDone;
    protected String description;

    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    // method to get status icon
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }
    // method to get isDone
    public boolean isDone() {
        return this.isDone;
    }
    // Mark task as done
    public void markAsDone() {
        this.isDone = true;
    }
    // Mark task as not done
    public void markUndone() {
        this.isDone = false;
    }
    // getter for description
    public String getDescription() {
        return this.description;
    }
    // return string of status and description as toString()
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String toSaveString() {
        String completed = this.isDone ? "1" : "0";
        return completed + " | " + this.getDescription();
    }
}
