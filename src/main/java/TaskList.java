import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }
}
