package seedu.volant.itinerary.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.Messages;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.model.Model;
import seedu.volant.itinerary.model.ItineraryModelManager;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all activities in itinerary whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " summer trip";

    private final TitleContainsKeywordsPredicate predicate;

    public FindCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ItineraryModelManager itineraryModel = ((ItineraryModelManager) model);
        ObservableList<Activity> currentList = itineraryModel.getFilteredActivityList();

        itineraryModel.updateFilteredActivityList(predicate);

        if (itineraryModel.getFilteredActivityList().isEmpty()) {
            itineraryModel.updateFilteredActivityList(itineraryModel.getPredicateShowAllActivities());
            return new CommandResult(
                    String.format("No activity with this keyword(s): "
                            + predicate.toString() + " found!", currentList.size()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_TRIPS_LISTED_OVERVIEW,
                            itineraryModel.getFilteredActivityList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
