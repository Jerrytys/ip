public class Task {

    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    // method to get status icon
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
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
}
