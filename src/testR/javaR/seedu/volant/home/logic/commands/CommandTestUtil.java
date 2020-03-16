package seedu.volant.home.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATERANGE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.volant.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.NameContainsKeywordsPredicate;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.EditTripDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TRIPNAME_GRADTRIP = "Graduation Trip 2020";
    public static final String VALID_TRIPNAME_CNY = "Chinese New Year Trip";

    public static final String VALID_LOCATION_GRADTRIP = "Bali, Indonesia";
    public static final String VALID_LOCATION_CNY = "Beijing, China";

    public static final String VALID_DATEFROM_GRADTRIP = "2020-04-25";
    public static final String VALID_DATETO_GRADTRIP = "2020-05-12";
    public static final String VALID_DATEFROM_CNY = "2020-01-20";
    public static final String VALID_DATETO_CNY = "2020-01-25";

    public static final String NAME_DESC_GRADTRIP = " " + PREFIX_NAME + VALID_TRIPNAME_GRADTRIP;
    public static final String NAME_DESC_CNY = " " + PREFIX_NAME + VALID_TRIPNAME_CNY;
    public static final String LOCATION_DESC_GRADTRIP = " " + PREFIX_LOCATION + VALID_LOCATION_GRADTRIP;
    public static final String LOCATION_DESC_CNY = " " + PREFIX_LOCATION + VALID_LOCATION_CNY;
    public static final String DATERANGE_DESC_GRADTRIP = " " + PREFIX_DATERANGE + VALID_DATEFROM_GRADTRIP + " to "
            + VALID_DATETO_GRADTRIP;
    public static final String DATERANGE_DESC_CNY = " " + PREFIX_DATERANGE + VALID_DATEFROM_CNY + " to "
            + VALID_DATETO_CNY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditTripDescriptorBuilder DESC_GRADTRIP;
    public static final EditTripDescriptorBuilder DESC_CNY;

    static {
        DESC_GRADTRIP = new EditTripDescriptorBuilder().withName(VALID_TRIPNAME_GRADTRIP)
                .withLocation(VALID_LOCATION_GRADTRIP).withDateRange(VALID_DATEFROM_GRADTRIP, VALID_DATETO_GRADTRIP);
        DESC_CNY = new EditTripDescriptorBuilder().withName(VALID_TRIPNAME_CNY)
                .withLocation(VALID_LOCATION_CNY).withDateRange(VALID_DATEFROM_CNY, VALID_DATETO_CNY);
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered trip list and selected trip in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TripList expectedAddressBook = new TripList(actualModel.getTripList());
        List<Trip> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTripList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getTripList());
        assertEquals(expectedFilteredList, actualModel.getFilteredTripList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the trip at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTripList().size());

        Trip trip = model.getFilteredTripList().get(targetIndex.getZeroBased());
        model.updateFilteredTripList(new NameContainsKeywordsPredicate(Arrays.asList(trip.getName().toString())));

        assertEquals(1, model.getFilteredTripList().size());
    }

}
