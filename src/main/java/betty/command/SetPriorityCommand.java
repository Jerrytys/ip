package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Priority;
import betty.task.Task;
import betty.task.TaskList;
import betty.ui.Ui;

public class SetPriorityCommand extends Command {
    private int taskNum;
    private Priority priority;
    public SetPriorityCommand(int taskNum, Priority priority) {
        super();
        this.taskNum = taskNum;
        this.priority = priority;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        Task task = taskList.get(this.taskNum);
        task.setPriority(this.priority);
        return ui.setPriority(task, this.priority);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
