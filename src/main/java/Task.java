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

    // mark task as done
    public void completeTask() {
        this.isDone = true;
    }

    // getter for description
    public String getDescription() {
        return this.description;
    }
}
