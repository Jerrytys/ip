package betty.task;

import betty.InvalidFormatException;
import betty.NoDescriptionException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

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
    public void addTodo(String args) throws NoDescriptionException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("todo");
        }
        addTask(new Todo(args));
    }
    // Add deadline
    public void addDeadline(String args) throws NoDescriptionException, InvalidFormatException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("deadline");
        }
        if (!args.contains("/by ")) {
            throw new InvalidFormatException("deadline must have a /by <time>");
        }
        String[] arguments = args.split("/by ", 2);
        addTask(new Deadline(arguments[0], arguments[1]));
    }
    // Add event
    public void addEvent(String args) throws NoDescriptionException, InvalidFormatException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("event");
        }
        if (!args.contains("/from ")) {
            throw new InvalidFormatException("event must have a /from <time>");
        }
        if (!args.contains("/to ")) {
            throw new InvalidFormatException(("event must have a /to <time>"));
        }
        String[] arguments = args.split("/from ", 2);
        String description = arguments[0];
        String[] time = arguments[1].split(" /to ", 2);
        String from = time[0];
        String to = time[1];
        addTask(new Event(description, from, to));
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
        for (Task t : this.taskList) {
            sb.append(t.toString()).append("\n");
        }
        return String.valueOf(sb);
    }
}
