package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ChangeTripCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = "";

    public static final String MESSAGE_SUCCESS = "Changed Directory to: %1$s";
    public static final String MESSAGE_ALREADY_AT_DIRECTORY = "Already at this directory.";

    private final String directory;

    public ChangeTripCommand(String directory) {
        requireNonNull(directory);
        this.directory = directory;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(directory, false, false);
    }
}
