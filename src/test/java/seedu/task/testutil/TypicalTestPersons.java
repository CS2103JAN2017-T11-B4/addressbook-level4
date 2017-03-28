package seedu.task.testutil;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.TaskList;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;

/**
 *
 */
public class TypicalTestPersons {

    public TestTask alice, benson, carl, daniel, elle, fiona, george, hoon, ida;
    public TestTask junk, kale, letOutDog;

    public TypicalTestPersons() {
        try {
            alice = new TaskBuilder().withDescription("Alice Pauline")
                    .withTags("friends").withTaskId(200).build();
            benson = new TaskBuilder().withDescription("Benson Meier")
                    .withTags("owesMoney", "friends").withTaskId(201).build();
            carl = new TaskBuilder()
                    .withDescription("Carl Kurz").withTaskId(202).build();
            daniel = new TaskBuilder()
                    .withDescription("Daniel Meier").withTaskId(203).build();
            elle = new TaskBuilder().withDescription("Elle Meyer")
                    .withTaskId(204).build();
            fiona = new TaskBuilder().withDescription("Fiona Kunz")
                    .withTaskId(205).build();
            george = new TaskBuilder()
                    .withDescription("George Best").withTaskId(206).build();

            // Manually added
            hoon = new TaskBuilder().withDescription("Hoon Meier")
                    .withTaskId(207)
                    .build();
            ida = new TaskBuilder().withDescription("Ida Mueller")
                    .withTaskId(208).build();
            junk = new TaskBuilder()
                    .withDescription("Junk to give away")
                    .withCompletion(true)
                    .withDuration("01/01/2017 0000", "01/01/2017 0100")
                    .withDueDate("01/01/2017 0100")
                    .withTaskId(300)
                    .build();
            kale = new TaskBuilder()
                    .withDescription("Kale for salad")
                    .withDuration("01/03/2017 0800", "01/03/2017 1200")
                    .withDueDate("01/03/2017 1800")
                    .withTaskId(301)
                    .build();
            letOutDog = new TaskBuilder()
                    .withDescription("Let out dog")
                    .withDuration("01/02/2017 0600", "01/02/2017 0630")
                    .withDueDate("01/02/2017 1100")
                    .withTaskId(302)
                    .build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(TaskList ab, TestTask[] data) {
        for (TestTask person : data) {
            try {
                ab.addTask(new Task(person));
            } catch (UniqueTaskList.DuplicateTaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalPersons() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    //@@evanyeung A0163744B
    public TestTask[] getTypicalTasksWithDates() {
        return new TestTask[]{junk, kale, letOutDog};
    }

    public TaskList getTypicalAddressBookWithDates() {
        TaskList ab = new TaskList();
        loadAddressBookWithSampleData(ab, getTypicalTasksWithDates());
        return ab;
    }
    //@@evanyeung

    public TaskList getTypicalAddressBook() {
        TaskList ab = new TaskList();
        loadAddressBookWithSampleData(ab, new TypicalTestPersons().getTypicalPersons());
        return ab;
    }
}
