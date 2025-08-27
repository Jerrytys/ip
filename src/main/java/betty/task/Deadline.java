package betty.task;

import betty.exception.BettyException;
import betty.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by, boolean isDone) throws BettyException {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + "[" + super.getStatusIcon() + "]"
                + super.toString() + " (by: " + this.by.format(dateFormat) + ")";
    }

    @Override
    public String toSaveString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String doneValue = super.isDone() ? "1" : "0";
        return "D | " + super.toSaveString() + "| " + this.by.format(dateFormat);
    }
}
