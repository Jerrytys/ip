package betty.parser;

import betty.command.*;
import betty.exception.BettyException;
import betty.task.Deadline;
import betty.task.Event;
import betty.task.Task;
import betty.task.Todo;
import betty.ui.Ui;


public class Parser {

    public static Task parseTask(String taskString) {
        // Create parsing for different cases of task
        String[] arguments = taskString.split(" \\| ", 5);
        String type = arguments[0];
        String completed = "0";
        String description = "";
        String deadline;
        String to;
        String from;

        switch (type) {
            case "T":
                completed = arguments[1];
                description = arguments[2];
                return new Todo(description);
            case "D":
                completed = arguments[1];
                description = arguments[2];
                deadline = arguments[3];
                return new Deadline(description, deadline);
            case "E":
                completed = arguments[1];
                description = arguments[2];
                from = arguments[3];
                to = arguments[4];
                return new Event(description, from, to);
            default:
               // TODO: THROW ERROR
                throw new IllegalArgumentException("Unknown task type spotted in file: " + type);
        }
    }

    public static Command parseCommand(String command) throws BettyException {
        // Create a ui class
        Ui ui = new Ui();
        // Listen for 2 argument
        String[] arguments = command.split(" ", 2);
        // First string is the type of command
        String commandName = arguments[0];
        // Second string, if present, is the number in list
        String commandArgs = arguments.length > 1 ? arguments[1] : "";
        // Info array to store further information of task
        String[] info;
        // Description String
        String description;
        // Task Number
        int taskNum;
        // Convert argument to command name
        Command.CommandName cm = Command.CommandName.fromString(commandName);
        switch (cm) {
            case BYE:
                // store TaskList into storage
                // stops program from running instead of repeating loop
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    taskNum = Integer.parseInt(commandArgs);
                } catch (NumberFormatException e) {
                    throw new BettyException("Please provide a number to mark");
                }
                return new MarkTaskCommand(taskNum);
            case UNMARK:
                try {
                    taskNum = Integer.parseInt(commandArgs);
                } catch (NumberFormatException e) {
                    throw new BettyException("Please provide a number to unmark");
                }
                return new UnmarkTaskCommand(taskNum);
            case TODO:
                if (commandArgs.isEmpty()) {
                    throw new BettyException("Description missing");
                }
                return new AddTodoCommand(new Todo(commandArgs));
            case DEADLINE:
                if (commandArgs.isEmpty()) {
                    throw new BettyException("Please include description and deadline for deadline task");
                }
                if (!commandArgs.contains("/by ")) {
                    throw new BettyException("deadline must have a /by <time>");
                }
                info = commandArgs.split("/by ", 2);
                description = info[0];
                String by = info[1];
                Deadline deadlineTask = new Deadline(description, by);
                return new AddDeadlineCommand(deadlineTask);
            case EVENT:
                if (commandArgs.isEmpty()) {
                    throw new BettyException("Please include description and duration for event task");
                }
                if (!commandArgs.contains("/from ")) {
                    throw new BettyException("event must have a /from <time>");
                }
                if (!commandArgs.contains("/to ")) {
                    throw new BettyException(("event must have a /to <time>"));
                }
                info = commandArgs.split("/from ", 2);
                description = arguments[0];
                String[] time = arguments[1].split(" /to ", 2);
                String from = time[0];
                String to = time[1];
                Event eventTask = new Event(description, from, to);
                return new AddEventCommand(eventTask);
            case DELETE:
                try {
                    taskNum = Integer.parseInt(commandArgs);
                } catch (NumberFormatException e) {
                    throw new BettyException("Please provide a number to delete");
                }
                return new DeleteCommand(taskNum);
            default:
                throw new BettyException("Unknown Command");
        }
    }
}
