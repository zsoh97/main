package seedu.volant.homepage.logic.commands;

import static seedu.volant.homepage.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.ModelManager;
import seedu.volant.homepage.model.UserPrefs;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Trip validTrip = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validTrip);

        assertCommandSuccess(new AddCommand(validTrip), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTrip), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Trip tripInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(tripInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
