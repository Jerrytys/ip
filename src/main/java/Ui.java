import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    // Helper function to print the chat messages
    public void printBox(String message) {
        System.out.println("-----------------------------------");
        System.out.println(message);
        System.out.println("-----------------------------------");
    }
    // Function to print greeting message
    public void greeting() {
        printBox("Hello! I'm Betty\nWhat can I do for you?");
    }
    // Function to print goodbye message
    public void goodbye() {
        printBox("Bye. Hope to see you again soon!");
    }

    // Displays the list of tasks
    public void displayList(TaskList taskList) {
        printBox(taskList.toString());
    }
    // Add task
    public void addTask(TaskList taskList) {
        printBox("Got it. I've added this task: \n" +
                "  " + taskList.get(taskList.size() - 1 ).toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    public void deleteTask(String message, Task task, TaskList taskList) {
        StringBuilder deleteMessage = new StringBuilder();
        deleteMessage.append("Noted, I've removed this task:\n")
                        .append(task.toString())
                            .append("\nNow you have ")
                                .append(taskList.size()).append(" tasks in the list.");
        printBox(String.valueOf(deleteMessage));
    }
    // Message for mark done and undone
    public void markDone(TaskList taskList, int number) {
        printBox("Nice! I've marked this task as done:\n" + taskList.get(number).toString());
    }
    public void markUndone(TaskList taskList, int number) {
        printBox("OK, I've marked this task as not done yet:\n" + taskList.get(number).toString());
    }
}
