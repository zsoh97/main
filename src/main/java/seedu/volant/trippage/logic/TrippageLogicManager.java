package seedu.volant.trippage.logic;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.Logic;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;

import java.nio.file.Path;

/**
 * Logic manager for trip page.
 */
public class TrippageLogicManager implements Logic {
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        return null;
    }

    @Override
    public Path getVolantFilePath() {
        return null;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }
}
