import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Betty {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    private static List<Task> list = new ArrayList<>();



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
                        addTodo(second, TaskFile);
                        break;
                    case DEADLINE:
                        addDeadline(second, TaskFile);
                        break;
                    case EVENT:
                        addEvent(second, TaskFile);
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
