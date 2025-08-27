package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        ui.displayList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
