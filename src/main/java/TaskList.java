import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        // printBox("Nice! I've marked this task as done:\n" + t.toString());
    }
    // Unmark task as not done
    public void markUndone(int number) {
        Task t = this.taskList.get(number);
        t.markUndone();
        // printBox("OK, I've marked this task as not done yet:\n" + t.toString());
    }
    // String for add task
    public void addTask(Task task, File TaskFile) {
        long taskNumber = 0;
        try {
            FileWriter fw = new FileWriter(TaskFile.getPath(), true);
            fw.write(task.toString() + "\n");
            fw.close();
            taskNumber = Files.lines(Path.of(TaskFile.getPath())).count();
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        printBox("Got it. I've added this task: \n" +
                "  " + task.toString() +
                "\nNow you have " + taskNumber + " tasks in the list.");
    }
    // Add task todo with exception
    public void addTodo(String args, File TaskFile) throws NoDescriptionException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("todo");
        }
        addTask(new Todo(args), TaskFile);
    }
    // Add deadline
    public void addDeadline(String args, File TaskFile) throws NoDescriptionException, InvalidFormatException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("deadline");
        }
        if (!args.contains("/by ")) {
            throw new InvalidFormatException("deadline must have a /by <time>");
        }
        String[] arguments = args.split("/by ", 2);
        addTask(new Deadline(arguments[0], arguments[1]), TaskFile);
    }
    // Add event
    public void addEvent(String args, File TaskFile) throws NoDescriptionException, InvalidFormatException {
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
        addTask(new Event(description, from, to), TaskFile);
    }
    // Delete task
    public void deleteTask(int number) {
        Task task = this.taskList.get(number);
        this.taskList.remove(number);
        StringBuilder message = new StringBuilder();
        message.append("Noted, I've removed this task:\n")
                .append("  " + task.toString())
                .append("\nNow you have " + this.taskList.size() + " tasks in the list.");
        printBox(String.valueOf(message));
    }
}
