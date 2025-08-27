package betty.task;

import betty.exception.BettyException;
import betty.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    // Test creation of event Task
    @Test
    public void event_createTask_success() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 1);
        Event event = new Event("go library", from, to, false);
        assertEquals("go library", event.getDescription());
        assertFalse(event.isDone());
        assertEquals(from, event.from);
        assertEquals(to, event.to);
    }

    //Test toString format of deadline Task
    @Test
    public void testToString_formatting() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 1);
        Event event = new Event("go library", from, to, true);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "[E][X] go library (from: " + expectedFrom + " to: " + expectedTo + ")";
        assertEquals(expected, event.toString());
    }

    // Test toSaveString formatting for storage
    @Test
    public void testToSaveString_formatting() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 5);
        Event event = new Event("go library", from, to, true);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "E | 1 | go library | " + expectedFrom + " | to: " + expectedTo;
        assertEquals(expected, event.toSaveString());
    }

    // Test invalid date parsing throws exception
    @Test
    public void testEvent_invalidDate_exception() {
        assertThrows(DateTimeParseException.class, () -> {
            LocalDate from = Parser.parseDate("invalid-from-date");
            LocalDate to = Parser.parseDate("invalid-to-date");
            new Event("invalid event", from, to, false);
        });
    }
}
