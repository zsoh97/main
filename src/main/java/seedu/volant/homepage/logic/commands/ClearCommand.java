package seedu.volant.homepage.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.TripList;

/**
 * Clears the trip list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Trip list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTripList(new TripList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
