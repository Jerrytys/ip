package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Task;
import betty.task.TaskList;
import betty.ui.Ui;

public class MarkTaskCommand extends Command {

    private int taskNum;

    public MarkTaskCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        Task task = taskList.get(this.taskNum);
        taskList.markDone(this.taskNum);
        ui.markDone(taskList, this.taskNum);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
