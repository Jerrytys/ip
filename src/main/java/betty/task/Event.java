package betty.task;

import betty.exception.BettyException;
import betty.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws BettyException {
        super(description);
        try {
            this.from = Parser.parseDate(from);
            this.to = Parser.parseDate(to);
        } catch (DateTimeParseException e) {
            throw new BettyException("Please input a valid date format");
        }

    }

    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "E | " + super.toString() + " | (from: " + this.from.format(dateFormat) +
                " | to: " + this.to.format(dateFormat) + ")";
    }
}
