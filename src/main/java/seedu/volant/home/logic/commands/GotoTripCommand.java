package seedu.volant.home.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.trip.Trip;


/**
 * Moves user to the trip page of a specific index.
 */
public class GotoTripCommand extends GotoCommand {

    public static final String FEEDBACK_TO_USER = "You are now in the trip page for TRIP : %s";
    private final Index targetIndex;

    public GotoTripCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        HomeModelManager homeModel = ((HomeModelManager) model);

        List<Trip> lastShownList = homeModel.getFilteredTripList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Trip targetTrip = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(FEEDBACK_TO_USER, targetTrip.getName().toString()), targetTrip);
    }
}
