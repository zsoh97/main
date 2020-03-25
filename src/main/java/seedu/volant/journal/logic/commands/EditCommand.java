package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_FEELING;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_WEATHER;

import java.util.HashMap;
import java.util.List;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.entry.Entry;

/**
 * Adds an entry to the Journal.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an entry in the journal. \n"
            + "Parameters: \n"
            + "INDEX "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_TEXT + "TEXT] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_FEELING + "FEELING] "
            + "[" + PREFIX_WEATHER + "WEATHER] \n"

            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_TEXT + "I love watermelon "
            + PREFIX_LOCATION + "Watermelon Centre ";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Entry edited: %1$s";
    public static final String MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX = "The entry index provided is invalid.";

    private final Index targetIndex;
    private final HashMap<String, Object> fieldsToEdit;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public <T extends Object> EditCommand(Index targetIndex, HashMap<String, Object> fieldsToEdit) {
        this.targetIndex = targetIndex;
        this.fieldsToEdit = fieldsToEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        JournalModelManager journalModel = ((JournalModelManager) model);

        List<Entry> lastShownList = journalModel.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToEdit = lastShownList.get(targetIndex.getZeroBased());
        journalModel.editEntry(entryToEdit, fieldsToEdit);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, entryToEdit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditCommand) other).targetIndex)); // state check
    }
}

