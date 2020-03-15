package seedu.volant.trip.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.trip.model.Journal;
import seedu.volant.trip.model.TripModelManager;

/**
 * Moves user from TRIP page to JOURNAL page.
 */
public class GotoJournalCommand extends GotoCommand {

    private static final String FEEDBACK_TO_USER = "You are now in the journal page for TRIP : %s";

    private Journal journal;

    /**
     * Constructs GotoJournalCommand with specified journal to go to.
     */
    public GotoJournalCommand(Journal journal) {
        this.journal = journal;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TripModelManager tripModel = ((TripModelManager) model);
        Trip currentTrip = tripModel.getTrip();

        return new CommandResult(String.format(FEEDBACK_TO_USER, currentTrip.getName().toString()), journal);
    }
}
