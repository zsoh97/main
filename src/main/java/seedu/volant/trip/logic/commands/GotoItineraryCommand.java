package seedu.volant.trip.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.model.Itinerary;
import seedu.volant.trip.model.TripModelManager;

/**
 * Moves user from TRIP page to ITINERARY page.
 */
public class GotoItineraryCommand extends GotoCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to the itinerary of a trip. \n"
            + "Tip:\t" + COMMAND_WORD + " i works as well.";
    private static final String FEEDBACK_TO_USER = "You are now in the itinerary page for TRIP: %s";

    private Itinerary itinerary;

    /**
     * Constructs GotoItineraryCommand with the itinerary to go to.
     */
    public GotoItineraryCommand(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TripModelManager tripModel = ((TripModelManager) model);
        Trip currentTrip = tripModel.getTrip();

        return new CommandResult(String.format(FEEDBACK_TO_USER, currentTrip.getName().toString()), itinerary);
    }

}
