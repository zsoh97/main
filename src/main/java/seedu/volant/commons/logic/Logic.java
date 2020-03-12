package seedu.volant.commons.logic;

import java.nio.file.Path;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the user prefs' volant file path.
     */
    Path getVolantFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
