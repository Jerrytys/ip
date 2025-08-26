package betty;

import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

import java.util.Scanner;

public class Betty {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Betty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            // Loads storage data file to taskList
            this.taskList = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }
    // Run the Betty bot object
    public void run() {
        // Greeting by chatbot
        ui.greeting();
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
                        // store TaskList into storage
                        storage.store(this.taskList);
                        ui.goodbye();
                        // stops program from running instead of repeating loop
                        return;
                    case LIST:
                        ui.displayList(this.taskList);
                        break;
                    case MARK:
                        int number = Integer.parseInt(second) - 1;
                        this.taskList.markDone(number);
                        ui.markDone(taskList, number);
                        break;
                    case UNMARK:
                        this.taskList.markUndone(Integer.parseInt(second) - 1);
                        break;
                    case TODO:
                        this.taskList.addTodo(second);
                        ui.addTask(taskList);
                        break;
                    case DEADLINE:
                        this.taskList.addDeadline(second);
                        ui.addTask(taskList);
                        break;
                    case EVENT:
                        this.taskList.addEvent(second);
                        ui.addTask(taskList);
                        break;
                    case DELETE:
                        taskList.deleteTask(Integer.parseInt(second) - 1);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (UnknownCommandException | NoDescriptionException | InvalidFormatException e) {
                ui.printBox(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printBox("Task number does not exist");
            }
            storage.store(this.taskList);
        }
    }

    public static void main(String[] args) {
        new Betty("./data/Betty.txt").run();
    }
}
