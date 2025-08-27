package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        storage.store(taskList);
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
