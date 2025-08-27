package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.task.Todo;
import betty.ui.Ui;

public class AddTodoCommand extends Command {

    private Todo todo;

    public AddTodoCommand(Todo todo) {
        super();
        this.todo = todo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addTodo(this.todo);
        ui.addTask(this.todo, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
