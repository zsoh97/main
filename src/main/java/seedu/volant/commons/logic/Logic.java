package seedu.volant.commons.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.storage.Storage;
import seedu.volant.home.logic.HomeLogicManager;

/**
 * API of the Logic component
 */
public interface Logic {

    String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    Logger LOGGER = LogsCenter.getLogger(HomeLogicManager.class);

    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the storage object.
     */
    Storage getStorage();

    /**
     * Returns the model object.
     */
    Model getModel();

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
