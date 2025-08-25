public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    UNKNOWN;

    // Create a method that converts input string to commandType
    public static Command fromString(String command) {
        switch(command.toLowerCase()) {
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "delete":
                return DELETE;
            default:
                return UNKNOWN;
        }
    }

}
