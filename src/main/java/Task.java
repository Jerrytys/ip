public class Task {

    private boolean isDone;
    private String description;

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
    // return string of status and description
    public String fullDescription() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
