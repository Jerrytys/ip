package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Task;
import betty.task.TaskList;
import betty.ui.Ui;

public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        Task task = taskList.get(taskNum);
        taskList.deleteTask(this.taskNum);
        ui.deleteTask(task, taskList);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
