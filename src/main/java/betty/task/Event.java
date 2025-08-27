package betty.task;

import betty.exception.BettyException;
import betty.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws BettyException {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString()
                + " (from: " + this.from.format(dateFormat)
                + " to: " + this.to.format(dateFormat) + ")";
    }

    @Override
    public String toSaveString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "E | " + super.toSaveString() + " | " + this.from.format(dateFormat) +
                " | to: " + this.to.format(dateFormat);
    }
}
