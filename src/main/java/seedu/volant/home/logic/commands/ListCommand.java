package seedu.volant.home.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;

/**
 * Lists all trips in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all trips";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HomeModelManager homeModel = ((HomeModelManager) model);
        homeModel.updateFilteredTripList(homeModel.getPredicateShowAllTrips());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
