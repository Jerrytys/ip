package betty.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import betty.command.*;
import betty.exception.BettyException;
import betty.task.Deadline;
import betty.task.Event;
import betty.task.Task;
import betty.task.Todo;
import betty.ui.Ui;

/**
 * Represents a parser class that can parse strings into other useful objects
 * Supports operation such as parseCommand, parseDate, parseTask
 */
public class Parser {
    // Create a list to store multiple formats of date
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MMM dd yyyy")
    );

    /**
     * Parses a task from a string representation in storage into a task that can be added into task list
     * @param taskString String representation of task from storage file
     * @return task that has been parsed
     * @throws BettyException BettyException if error in parsing task
     */
    public static Task parseTask(String taskString) throws BettyException {
        // Create parsing for different cases of task
        String[] arguments = taskString.split(" \\| ", 5);
        String type = arguments[0];
        String completed = "0";
        boolean isDone = false;
        String description = "";
        LocalDate deadline;
        LocalDate to;
        LocalDate from;

        switch (type) {
        case "T":
            completed = arguments[1];
            isDone = completed.equals("1");
            description = arguments[2];
            return new Todo(description, isDone);
        case "D":
            completed = arguments[1];
            isDone = completed.equals("1");
            description = arguments[2];
            deadline = parseDate(arguments[3]);
            return new Deadline(description, deadline, isDone);
        case "E":
            completed = arguments[1];
            isDone = completed.equals("1");
            description = arguments[2];
            from = parseDate(arguments[3]);
            to = parseDate(arguments[4]);
            return new Event(description, from, to, isDone);
        default:
            // TODO: THROW ERROR
            throw new IllegalArgumentException("Unknown task type spotted in file: " + type);
        }
    }

    /**
     * Parse commands given by users into useful commands to be interpreted by the task manager object
     * @param command String representation of command inputted by user
     * @return a command to be executed by the task manager
     * @throws BettyException BettyException if there is error in parsing command
     */
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
            return new AddTodoCommand(new Todo(commandArgs, false));
        case DEADLINE:
            if (commandArgs.isEmpty()) {
                throw new BettyException("Please include description and deadline for deadline task");
            }
            if (!commandArgs.contains("/by ")) {
                throw new BettyException("deadline must have a /by <time>");
            }
            info = commandArgs.split("/by ", 2);
            description = info[0];
            if (description.isEmpty()) {
                throw new BettyException("Please include description for deadline task");
            }
            LocalDate by = Parser.parseDate(info[1]);
            Deadline deadlineTask = new Deadline(description, by, false);
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
            description = info[0];
            if (description.isEmpty()) {
                throw new BettyException("Please include description for event task");
            }
            String[] time = info[1].split(" /to ", 2);
            LocalDate from = parseDate(time[0]);
            LocalDate to = parseDate(time[1]);
            Event eventTask = new Event(description, from, to, false);
            return new AddEventCommand(eventTask);
        case DELETE:
            try {
                taskNum = Integer.parseInt(commandArgs);
            } catch (NumberFormatException e) {
                throw new BettyException("Please provide a number to delete");
            }
            return new DeleteCommand(taskNum);
        case FIND:
            if (commandArgs.isEmpty()) {
                throw new BettyException("Please include description to find");
            }
            return new FindCommand(commandArgs);
        default:
            throw new BettyException("Unknown Command");
        }
    }

    /**
     * Parses date string representation into a LocalDate object
     * @param date String representation of date provided by user
     * @return LocalDate object after string representation of date has been parsed
     */
    public static LocalDate parseDate(String date) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }
        // if no format matches, invalid date format;
        throw new DateTimeParseException("Please input a valid date format", date, 0);
    }
}
