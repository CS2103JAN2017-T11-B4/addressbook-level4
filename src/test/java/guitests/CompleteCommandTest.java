package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.task.logic.commands.CompleteCommand.MESSAGE_COMPLETE_TASK_SUCCESS;

import org.junit.Test;

import seedu.task.testutil.TestTask;
import seedu.task.testutil.TestUtil;

public class CompleteCommandTest extends TaskListGuiTest {

    @Test
    public void complete() {

        //complete the first in the list
        TestTask[] currentList = td.getTypicalPersons();
        int targetIndex = 1;
        assertCompleteSuccess(targetIndex, currentList);


        //invalid index
        commandBox.runCommand("complete " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");

    }

    /**
     * Runs the complete command to complete the task at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. index 1 to complete the first person in the list,
     * @param currentList A copy of the current list of persons (before completion).
     */
    private void assertCompleteSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToComplete = currentList[targetIndexOneIndexed - 1]; // -1 as array uses zero indexing
        TestTask[] expectedRemainder = TestUtil.completeTaskInList(currentList, targetIndexOneIndexed);

        commandBox.runCommand("complete " + targetIndexOneIndexed);

        //confirm the list now is the same as the first task is completed
        assertTrue(personListPanel.isListMatching(expectedRemainder));

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_COMPLETE_TASK_SUCCESS, taskToComplete));
    }


}
