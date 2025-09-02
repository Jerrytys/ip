package betty;

import betty.command.Command;
import betty.exception.BettyException;
import betty.parser.Parser;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

import java.util.Scanner;

/**
 * Represents the Betty object which is the task manager bot application
 * Betty manages the task list by parsing user commands, executing commands, and
 * saving updates to persistent storage
 */
public class Betty {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a new Betty chatbot using basic filePath "./data/betty.txt"
     */
    public Betty() {
        this.ui = new Ui();
        this.storage = new Storage("./data/Betty.txt");
        try {
            // Loads storage data file to taskList
            this.taskList = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }
    /**
     * Constructs a new Betty chatbot
     * @param filePath filePath for persistent storage of tasks using for storing/loading of task list
     */
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

    /**
     * Runs the chatbot. Handles main loop of reading user command, parsing and executing commands
     */
    public void run() {
        // track when to exit
        boolean isExit = false;
        // Greeting by chatbot
        this.ui.greeting();

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (BettyException e) {
                ui.printError(e.getMessage());
            }
        }
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parseCommand(input);
            response = c.execute(this.taskList, this.ui, this.storage);
            return response;
        } catch (BettyException e) {
            return "Error";
        }
    }
    public String getGreeting() {
        return this.ui.greeting();
    }
    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new Betty("./data/Betty.txt").run();
    }
}
