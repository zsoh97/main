package seedu.volant.home.logic.commands;

import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.home.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.volant.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.volant.testutil.TypicalTrips.getTypicalTripList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.HomeModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new HomeModelManager(getTypicalTripList(), new UserPrefs());
        expectedModel = new HomeModelManager(model.getTripList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
