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
    public String printBox(String message) {
        StringBuilder result = new StringBuilder();
        result.append("-----------------------------------\n")
                        .append(message).append("\n")
                        .append("-----------------------------------");
        return String.valueOf(result);
    }
    // Function to print greeting message
    public String greeting() {
        return printBox("Hello! I'm Betty\nWhat can I do for you?");
    }
    // Function to print goodbye message
    public String goodbye() {
        return printBox("Bye. Hope to see you again soon!");
    }

    // Displays the list of tasks
    public String displayList(TaskList taskList) {
        return printBox(taskList.toString());
    }

    /**
     * Prints the message for adding a task to the task list
     * @param task task to be added
     * @param taskList task list where task is added
     */
    public String addTask(Task task, TaskList taskList) {
        return printBox("Got it. I've added this task: \n"
                            + "  " + task.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Prints the message for deleting a task to the task list
     * @param task task to be deleted
     * @param taskList task list where task is deleted
     */
    public String deleteTask(Task task, TaskList taskList) {
        StringBuilder deleteMessage = new StringBuilder();
        deleteMessage.append("Noted, I've removed this task:\n")
                        .append(task.toString())
                            .append("\nNow you have ")
                                .append(taskList.size()).append(" tasks in the list.");
        return printBox(String.valueOf(deleteMessage));
    }
    // Message for mark done and undone
    public String markDone(TaskList taskList, int number) {
        return printBox("Nice! I've marked this task as done:\n" + taskList.get(number).toString());
    }
    public String markUndone(TaskList taskList, int number) {
        return printBox("OK, I've marked this task as not done yet:\n" + taskList.get(number).toString());
    }
    // Read command from scanner
    public String readCommand() {
        return this.input.nextLine();
    }
    // Print error message
    public String printError(String message) {
        return printBox(message);
    }

    /**
     * Display the filtered list given a filtered task list
     * @param filtered filtered task list to be displayed
     */
    public String displayFilteredList(TaskList filtered) {
        String message = "Here are the matching tasks in your list :\n";
        return printBox(message + filtered.toString());
    }
}
