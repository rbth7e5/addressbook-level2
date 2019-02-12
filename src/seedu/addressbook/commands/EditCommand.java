package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person's name in the address book. "
            + "Parameters: NAME n/NEW_NAME\n"
            + "Example: " + COMMAND_WORD
            + " John Doe n/Johnny Doe";

    public static final String MESSAGE_SUCCESS = "%1$s is now called %2$s";
    public static final String MESSAGE_MISSING_PERSON = "This person does not exist in the address book";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Name toEdit;
    private final Name toReplace;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String name, String replacement) throws IllegalValueException {
        this.toEdit = new Name(name);
        this.toReplace = new Name(replacement);
    }

    public EditCommand(Name toEdit, Name toReplace) {
        this.toEdit = toEdit;
        this.toReplace = toReplace;
    }

    public Name getName() {
        return toEdit;
    }

    @Override
    public CommandResult execute() {
        try {
            Person personToEdit = addressBook.getPerson(toEdit);
            Person editedPerson = new Person(
                    toReplace,
                    personToEdit.getPhone(),
                    personToEdit.getEmail(),
                    personToEdit.getAddress(),
                    personToEdit.getTags()
            );
            addressBook.removePerson(personToEdit);
            addressBook.addPerson(editedPerson);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit, toReplace));
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(MESSAGE_MISSING_PERSON);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}
