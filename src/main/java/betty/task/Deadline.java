package betty.task;

import betty.exception.BettyException;
import betty.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws BettyException {
        super(description);
        try {
            this.by = Parser.parseDate(by);
        } catch (DateTimeParseException e){
            throw new BettyException("Please input a valid date format");
        }
    }

    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "D | " + super.toString() + "| (by: " + this.by.format(dateFormat) + ")";
    }
}
