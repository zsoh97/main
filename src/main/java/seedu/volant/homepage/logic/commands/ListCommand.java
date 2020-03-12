package seedu.volant.homepage.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.homepage.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.model.Model;

/**
 * Lists all trips in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all trips";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
