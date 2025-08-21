import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Betty {

    private static List<Task> list = new ArrayList<>();
    // Helper function to print the chat messages
    public static void printBox(String message) {
        System.out.println("-----------------------------------");
        System.out.println(message);
        System.out.println("-----------------------------------");
    }

    public static void greeting() {
        printBox("Hello! I'm Betty\nWhat can I do for you?");
    }
    // Displays the list of tasks
    public static void displayList() {
        int count = 1;
        StringBuilder message = new StringBuilder();
        for (Task item : list) {
            message.append(count).append(". ").append(item.toString()).append("\n");
            count++;
        }
        printBox(String.valueOf(message));
    }
    // Mark task as done
    public static void markDone(int number) {
        Task t = list.get(number);
        t.markAsDone();
        printBox("Nice! I've marked this task as done:\n" + t.toString());
    }
    // Unmark task as not done
    public static void markUndone(int number) {
        Task t = list.get(number);
        t.markUndone();
        printBox("OK, I've marked this task as not done yet:\n" + t.toString());
    }
    // String for add task
    public static void addTask(Task task) {
        list.add(task);
        printBox("Got it. I've added this task: \n" +
                "  " + task.toString() +
                "\nNow you have " + list.size() + " tasks in the list.");
    }
    // Add task todo with exception
    public static void addTodo(String args) throws NoDescriptionException {
        if (args.isEmpty()) {
            throw new NoDescriptionException("todo");
        }
        addTask(new Todo(args));
    }
    // Add deadline
    public static void addDeadline(String args) throws NoDescriptionException, InvalidFormatException {
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
    public static void addEvent(String args) throws NoDescriptionException, InvalidFormatException {
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

    public static void main(String[] args) throws InvalidFormatException {
        // Greeting by chatbot
        greeting();
        // Create scanner to take input by user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Listen for input by user
            String input = scanner.nextLine();
            // Listen for 2 argument
            String[] arguments = input.split(" ", 2);
            // First string is the type of command
            String first = arguments[0];
            // Second string, if present, is the number in list
            String second = arguments.length > 1 ? arguments[1] : "";

            // Break when condition "bye" is met, using try catch block for exception
            try {
                if (input.equals("bye")) {
                    printBox("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    displayList();
                } else if (first.equals("mark")) {
                    markDone(Integer.parseInt(second) - 1);
                } else if (first.equals("unmark")) {
                    markUndone(Integer.parseInt(second) - 1);
                } else if (first.equals("todo")) {
                    addTodo(second);
                } else if (first.equals("deadline")) {
                    addDeadline(second);
                } else if (first.equals("event")) {
                    addEvent(second);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException | NoDescriptionException | InvalidFormatException e) {
                printBox(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                printBox("Task number does not exist");
            }
        }
    }
}
