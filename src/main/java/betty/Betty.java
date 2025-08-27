package betty;

import betty.exception.BettyException;
import betty.command.Command;
import betty.parser.Parser;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

import java.util.Scanner;

public class Betty {

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

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

    public static void main(String[] args) {
        new Betty("./data/Betty.txt").run();
    }
}
