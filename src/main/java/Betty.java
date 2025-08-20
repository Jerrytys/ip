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
            System.out.println(count + ". "
                                + "[" + item.getStatusIcon()
                                + "] " + item.getDescription());
            count++;
        }
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        // Greeting by chatbot
        greeting();

        // Create scanner to take input by user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Listen for input by user
            String input = scanner.nextLine();
            // Break when condition "bye" is met
            if (input.equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                break;
            } else if (input.equals("list")) {
                displayList();
            } else {
                System.out.println("-----------------------------------");
                System.out.println("added: " + input);
                System.out.println("-----------------------------------");
                list.add(new Task(input));
            }
        }


    }
}
