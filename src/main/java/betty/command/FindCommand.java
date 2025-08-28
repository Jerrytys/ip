package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

public class FindCommand extends Command {

    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        TaskList filtered = taskList.find(filter);
        ui.displayFilteredList(filtered);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
