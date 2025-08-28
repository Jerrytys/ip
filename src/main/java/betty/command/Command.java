package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

public abstract class Command {
    public enum CommandName {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        UNKNOWN;

        // Create a method that converts input string to commandType
        public static CommandName fromString(String command) {
            return switch (command.toLowerCase()) {
                case "bye" -> BYE;
                case "list" -> LIST;
                case "mark" -> MARK;
                case "unmark" -> UNMARK;
                case "todo" -> TODO;
                case "deadline" -> DEADLINE;
                case "event" -> EVENT;
                case "delete" -> DELETE;
                case "find" -> FIND;
                default -> UNKNOWN;
            };
        }
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException;

    public abstract boolean isExit();
}
