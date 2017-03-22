//@@kellyli A0164103W
package seedu.task.logic.commands;

import java.util.Optional;

import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.logic.history.TaskMemento;
import seedu.task.model.task.Task;
import seedu.task.model.task.TaskId;
import seedu.task.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.task.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Undo previous commands executed
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Command undone";
    public static final String MESSAGE_NO_HISTORY = "No commands to undo";

    @Override
    public CommandResult execute() throws CommandException {
        final Optional<TaskMemento> memento = mementos.getUndoMemento();
        final Task mementoTask = memento.orElseThrow(
            () -> new CommandException(MESSAGE_NO_HISTORY)).oldTask;
        final TaskId mementoTaskId = memento.orElse(null).taskId;
        Task taskToBeReplaced = model.getTaskList().getTaskById(mementoTaskId);

        if (mementoTask == null && taskToBeReplaced != null) { //In case of undoing add
            try {
                model.deleteTask(taskToBeReplaced);
            } catch (TaskNotFoundException e) {
                assert false : "The target task cannot be missing";
            }
        }

        if (taskToBeReplaced == null && mementoTask != null) { //In case of undoing delete
            try {
                model.addTask(mementoTask);
            } catch (DuplicateTaskException e) {
                assert false : "Adding duplicate task";
            }
        }

        if (taskToBeReplaced != null && mementoTask != null) { //In case of editing
            try {
                model.updateTaskById(mementoTaskId, mementoTask);
            } catch (DuplicateTaskException e) {
                assert false : "duplicate task";
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}