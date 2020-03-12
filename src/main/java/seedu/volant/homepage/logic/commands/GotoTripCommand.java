package seedu.volant.homepage.logic.commands;

import java.util.List;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.trip.Trip;

import static java.util.Objects.requireNonNull;

public class GotoTripCommand extends GotoCommand {
    private final Index targetIndex;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Goes to a trip in the trip list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public GotoTripCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Trip> lastShownList = model.getFilteredTripList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        model.gotoTrip(targetIndex);
        return new CommandResult(String.format(Messages.MESSAGE_MOVED_TO_TRIP, targetIndex.getOneBased()),
                lastShownList.get(targetIndex.getZeroBased()));
    }
}
