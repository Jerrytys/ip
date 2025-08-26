import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static void displayList(File TaskFile) {
        int count = 1;
        StringBuilder message = new StringBuilder();
        try {
            Scanner scanner = new Scanner(TaskFile);
            while (scanner.hasNextLine()) {
                message.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
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
    // Delete task
    public static void deleteTask(int number) {
        Task task = list.get(number);
        list.remove(number);
        StringBuilder message = new StringBuilder();
        message.append("Noted, I've removed this task:\n")
                .append("  " + task.toString())
                .append("\nNow you have " + list.size() + " tasks in the list.");
        printBox(String.valueOf(message));
    }
    // Get file from hard disk
    public static File getFile(String path) {
        File myFile = new File(path);
        try {
            // Create directories if not present
            File parent = myFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            // If file does not exist, create a new file
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return myFile;
    }

    public static void main(String[] args) throws InvalidFormatException {
        // Greeting by chatbot
        greeting();
        // Create/Access file that stored Tasks
        File TaskFile = getFile("./data/Betty.txt");
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

            // Convert to command in enum class Command
            Command command = Command.fromString(first);

            // Switch case using enums
            try {
                switch (command) {
                    case BYE:
                        printBox("Bye. Hope to see you again soon!");
                        // stops program from running instead of repeating loop
                        return;
                    case LIST:
                        displayList(TaskFile);
                        break;
                    case MARK:
                        markDone(Integer.parseInt(second) - 1);
                        break;
                    case UNMARK:
                        markUndone(Integer.parseInt(second) - 1);
                        break;
                    case TODO:
                        addTodo(second);
                        break;
                    case DEADLINE:
                        addDeadline(second);
                        break;
                    case EVENT:
                        addEvent(second);
                        break;
                    case DELETE:
                        deleteTask(Integer.parseInt(second) - 1);
                        break;
                    default:
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
