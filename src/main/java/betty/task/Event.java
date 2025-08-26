package betty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);

        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "E | " + super.toString() + " | (from: " + this.from.format(dateFormat) +
                " | to: " + this.to.format(dateFormat) + ")";
    }
}
