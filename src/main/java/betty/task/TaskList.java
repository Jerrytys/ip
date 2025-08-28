package betty.task;

import betty.exception.BettyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a TaskList object that stores a list of task
 * Provides operations to add, delete, retrieve and update tasks
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Constructs a TaskList with an existing list of tasks
     * @param taskList the list of task to initialise with
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    /**
     * Constructs a TaskList with an empty list of tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list
     * @return the size of the tas list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the task at the given index
     * @param index the position of the task in the list
     * @return the task at the given index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Add the given task into the list
     * @param task task to be added into list
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks the task number in the list as completed
     * @param number index of task to be marked as complete
     */
    public void markDone(int number) {
        Task t = this.taskList.get(number);
        t.markAsDone();
    }

    /**
     * Marks the task number in the list as not completed
     * @param number index of task to be marked as complete
     */
    public void markUndone(int number) {
        Task t = this.taskList.get(number);
        t.markUndone();
    }

    /**
     * Add a todo task into the list
     * @param todo task to be added into list
     * @throws BettyException BettyException if unable to add task
     */
    public void addTodo(Todo todo) throws BettyException {
        addTask(todo);
    }

    /**
     * Add a deadline task into the list
     * @param deadline task to be added into list
     * @throws BettyException BettyException if unable to add task
     */
    public void addDeadline(Deadline deadline) throws BettyException {
        addTask(deadline);
    }

    /**
     * Add an event task into the list
     * @param eventTask task to be added into list
     * @throws BettyException BettyException if unable to add task
     */
    public void addEvent(Event eventTask) throws BettyException {
        addTask(eventTask);
    }

    /**
     * Deletes the task at the given index in the list
     * @param number index of the task to be deleted
     */
    public void deleteTask(int number) {
        Task task = this.taskList.get(number);
        this.taskList.remove(number);
    }

    /**
     * Returns the string representation of the task list for display purposes
     * @return a formatted string with each individual task
     */
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

    /**
     * Returns the string representation of the task list for storage purposes
     * @return a formatted string with each individual task for storage
     */
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.taskList) {
            sb.append(t.toSaveString()).append("\n");
        }
        return String.valueOf(sb);
    }
}
