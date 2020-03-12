package seedu.volant.homepage.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.volant.commons.core.Messages;
import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.trip.Trip;

/**
 * Deletes a trip identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the trip identified by the index number used in the displayed trip list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Trip: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Trip> lastShownList = model.getFilteredTripList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Trip tripToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTrip(tripToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, tripToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
