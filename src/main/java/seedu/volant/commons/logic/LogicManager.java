package seedu.volant.commons.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.parser.InputParser;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.ReadOnlyTripList;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InputParser inputParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        inputParser = new InputParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = inputParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTripList(model.getTripList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public ReadOnlyTripList getTripList() {
        return model.getTripList();
    }

    public ObservableList<Trip> getFilteredTripList() {
        return model.getFilteredTripList();
    }

    @Override
    public Path getVolantFilePath() {
        return model.getVolantFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
