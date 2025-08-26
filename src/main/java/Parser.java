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
}
