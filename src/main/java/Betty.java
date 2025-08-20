import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Betty {

    private static List<Task> list = new ArrayList<>();
    public static void greeting() {
        System.out.println("-----------------------------------");
        System.out.println("Hello! I'm Betty");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");
    }

    public static void displayList() {
        int count = 1;
        System.out.println("-----------------------------------");
        System.out.println("Here are the tasks in your list");
        for (Task item : list) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
        System.out.println("-----------------------------------");
    }
    // Mark task as done
    public static void markDone(int number) {
        Task t = list.get(number);
        t.markAsDone();
        System.out.println("-----------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
        System.out.println("-----------------------------------");
    }
    // Unmark task as not done
    public static void markUndone(int number) {
        Task t = list.get(number);
        t.markUndone();
        System.out.println("-----------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        System.out.println("-----------------------------------");
    }
    // String for add task
    public static void addTask(Task task) {
        list.add(task);
        System.out.println("-----------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("-----------------------------------");
    }
    // Add task todo
    public static void addTodo(String args) {
        addTask(new Todo(args));
    }
    // Add deadline
    public static void addDeadline(String args) {
        String[] arguments = args.split("/by ", 2);
        addTask(new Deadline(arguments[0], arguments[1]));
    }
    // Add event
    public static void addEvent(String args) {
        String[] arguments = args.split("/from ", 2);
        String description = arguments[0];
        String[] time = arguments[1].split(" /to ", 2);
        String from = time[0];
        String to = time[1];
        addTask(new Event(description, from, to));
    }

    public static void main(String[] args) {
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

            // Break when condition "bye" is met
            if (input.equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
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
                System.out.println("-----------------------------------");
                System.out.println("added: " + input);
                System.out.println("-----------------------------------");
                list.add(new Task(input));
            }
        }
    }
}
