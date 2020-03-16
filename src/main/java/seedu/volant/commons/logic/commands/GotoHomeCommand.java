package seedu.volant.commons.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.trip.model.TripModelManager;

// TODO: Complete this implementation

/**
 * Returns to HOME page from any page.
 */
public class GotoHomeCommand extends GotoCommand {

    public static final String RESULT_INVALID = "You are already on the home page.";
    public static final String RESULT_SUCCESS = "You are now on the home page.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // If in a trip page
        if (model instanceof TripModelManager) {
            TripModelManager t = ((TripModelManager) model);
            return new CommandResult(RESULT_SUCCESS, t.getTripList());
        }

        // If in the itinerary page
        if (model instanceof ItineraryModelManager) {
            ItineraryModelManager t = ((ItineraryModelManager) model);
            return new CommandResult(RESULT_SUCCESS, t.getTrip());
        }

        // If in the journal page
        if (model instanceof JournalModelManager) {
            JournalModelManager t = ((JournalModelManager) model);
            return new CommandResult(RESULT_SUCCESS, t.getTrip());
        }

        // If in the home page
        if (model instanceof HomeModelManager) {
            throw new CommandException(RESULT_INVALID);
        }

        throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
    }
}
