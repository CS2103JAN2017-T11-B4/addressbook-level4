package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.task.logic.commands.UncompleteCommand.MESSAGE_UNCOMPLETE_TASK_SUCCESS;

import org.junit.Test;

import seedu.task.testutil.TestTask;
import seedu.task.testutil.TestUtil;

public class UncompleteCommandTest extends TaskListGuiTest {

    @Test
    public void uncomplete() {

        //complete the first in the list
        TestTask[] currentList = td.getTypicalPersons();
        int targetIndex = 1;
        assertUncompleteSuccess(targetIndex, currentList);


        //invalid index
        commandBox.runCommand("uncomplete " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");

    }

    /**
     * Runs the complete command to uncomplete the person at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. index 1 to uncomplete the first person in the list,
     * @param currentList A copy of the current list of persons (before uncompletion).
     */
    private void assertUncompleteSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToUncomplete = currentList[targetIndexOneIndexed - 1]; // -1 as array uses zero indexing
        TestTask[] expectedRemainder = TestUtil.uncompleteTaskInList(currentList, targetIndexOneIndexed);

        commandBox.runCommand("uncomplete " + targetIndexOneIndexed);

        //confirm the list now is the same as the first task is uncompleted
        assertTrue(personListPanel.isListMatching(expectedRemainder));

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_UNCOMPLETE_TASK_SUCCESS, taskToUncomplete));
    }


}
