package betty.ui;

import betty.task.Task;
import betty.task.TaskList;

import java.util.Scanner;

public class Ui {

    // Scanner to track input by user
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }
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
    public void addTask(Task task, TaskList taskList) {
        printBox("Got it. I've added this task: \n" +
                "  " + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    public void deleteTask(Task task, TaskList taskList) {
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
    // Read command from scanner
    public String readCommand() {
        return this.input.nextLine();
    }
    // Print error message
    public void printError(String message) {
        printBox(message);
    }

    public void displayFilteredList(TaskList filtered) {
        String message = "Here are the matching tasks in your list :\n";
        printBox(message + filtered.toString());
    }
}
