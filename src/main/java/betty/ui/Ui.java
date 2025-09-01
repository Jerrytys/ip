package betty.ui;

import java.util.Scanner;

import betty.task.Task;
import betty.task.TaskList;

/**
 * Represents an Ui object that prints out messages by the chatbot
 */
public class Ui {

    // Scanner to track input by user
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Wraps a message between 2 lines, creating a box with message inside
     * @param message the message to be wrapped
     */
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

    /**
     * Prints the message for adding a task to the task list
     * @param task task to be added
     * @param taskList task list where task is added
     */
    public void addTask(Task task, TaskList taskList) {
        printBox("Got it. I've added this task: \n"
                + "  " + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Prints the message for deleting a task to the task list
     * @param task task to be deleted
     * @param taskList task list where task is deleted
     */
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

    /**
     * Display the filtered list given a filtered task list
     * @param filtered filtered task list to be displayed
     */
    public void displayFilteredList(TaskList filtered) {
        String message = "Here are the matching tasks in your list :\n";
        printBox(message + filtered.toString());
    }
}
