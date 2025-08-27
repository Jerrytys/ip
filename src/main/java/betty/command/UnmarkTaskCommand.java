package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

public class UnmarkTaskCommand extends Command {

    private int taskNum;

    public UnmarkTaskCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.markUndone(this.taskNum);
        ui.markUndone(taskList, this.taskNum);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
