package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Deadline;
import betty.task.TaskList;
import betty.ui.Ui;

public class AddDeadlineCommand extends Command {

    private Deadline deadlineTask;

    public AddDeadlineCommand(Deadline deadlineTask) {
        super();
        this.deadlineTask = deadlineTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addDeadline(this.deadlineTask);
        ui.addTask(this.deadlineTask, taskList);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
