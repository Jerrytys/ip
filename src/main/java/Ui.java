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

    // Displays the list of tasks
    public void displayList(File TaskFile) {
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
}
