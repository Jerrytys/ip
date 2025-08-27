package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Event;
import betty.task.TaskList;
import betty.ui.Ui;

public class AddEventCommand extends Command {

    private Event eventTask;

    public AddEventCommand(Event eventTask) {
        this.eventTask = eventTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addEvent(this.eventTask);
        ui.addTask(this.eventTask, taskList);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
