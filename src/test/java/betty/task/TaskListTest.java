package betty.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void createAllThreeTaskSuccess() throws Exception {
        TaskList tasks = new TaskList();
        tasks.addTodo("read book");
        tasks.addDeadline("return book /by 2025-09-01");
        tasks.addEvent("go to library /from 2025-09-01 /to 2025-09-01");

        assertEquals(3, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);
        assertTrue(tasks.get(1) instanceof Deadline);
        assertTrue(tasks.get(2) instanceof Event);
    }
}
