package betty.task;

import betty.exception.BettyException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // size of taskList
    public int size() {
        return this.taskList.size();
    }
    // get specific task
    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    // Mark task as done
    public void markDone(int number) {
        Task t = this.taskList.get(number);
        t.markAsDone();
    }
    // Unmark task as not done
    public void markUndone(int number) {
        Task t = this.taskList.get(number);
        t.markUndone();
    }

    // Add task todo with exception
    public void addTodo(Todo todo) throws BettyException {
        addTask(todo);
    }
    // Add deadline
    public void addDeadline(Deadline deadline) throws BettyException {
        addTask(deadline);
    }
    // Add event
    public void addEvent(Event eventTask) throws BettyException {
        addTask(eventTask);
    }
    // Delete task
    public void deleteTask(int number) {
        Task task = this.taskList.get(number);
        this.taskList.remove(number);
    }

    // return string representation of list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Task t : this.taskList) {
            sb.append(String.valueOf(counter)).append(". ");
            sb.append(t.toString()).append("\n");
            counter++;
        }
        return String.valueOf(sb);
    }

    // return string representation to save into file
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.taskList) {
            sb.append(t.toSaveString()).append("\n");
        }
        return String.valueOf(sb);
    }
}
