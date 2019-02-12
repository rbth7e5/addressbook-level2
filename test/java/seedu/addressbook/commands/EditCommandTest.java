package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;

import static org.junit.Assert.fail;

public class EditCommandTest {
    @Test
    public void editCommand_invalidName_throwsException() {
        final String[] invalidNames = { "", " ", "[]\\[;]" };
        for (String name : invalidNames) {
            assertConstructingInvalidEditCmdThrowsException(name, Name.EXAMPLE);
        }
    }

    @Test
    public void editCommand_invalidReplacement_throwsException() {
        final String[] invalidNames = { "", " ", "[]\\[;]" };
        for (String replacement : invalidNames) {
            assertConstructingInvalidEditCmdThrowsException(Name.EXAMPLE, replacement);
        }
    }

    /**
     * Asserts that attempting to construct an edit command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidEditCmdThrowsException(String name, String replacement) {
        try {
            new EditCommand(name, replacement);
        } catch (IllegalValueException e) {
            return;
        }
        String error = String.format(
                "An edit command was successfully constructed with invalid input: %s %s",
                name, replacement);
        fail(error);
    }
}
