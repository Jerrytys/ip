import java.util.Scanner;

public class Betty {

    public static void greeting() {
        System.out.println("-----------------------------------");
        System.out.println("Hello! I'm Betty");
        System.out.println("What can I do for you?");
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
            } else {
                System.out.println("-----------------------------------");
                System.out.println(input);
                System.out.println("-----------------------------------");
            }
        }


    }
}
