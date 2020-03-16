package seedu.volant.home.logic;

import java.io.IOException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.storage.Storage;
import seedu.volant.home.logic.parser.HomeInputParser;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;

/**
 * The logic manager for the HOME page.
 */
public class HomeLogicManager implements Logic {

    private final HomeModelManager model;
    private final Storage storage;
    private final HomeInputParser inputParser = new HomeInputParser();

    public HomeLogicManager(HomeModelManager model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    public HomeModelManager getModel() {
        return model;
    }

    public Storage getStorage() {
        return storage;
    }

    public TripList getTripList() {
        return model.getTripList();
    }

    public ReadOnlyTripList getReadOnlyTripList() {
        return model.getTripList();
    }

    public ObservableList<Trip> getFilteredTripList() {
        return model.getFilteredTripList();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        LOGGER.info("----------------[USER COMMAND IN HOME][" + commandText + "]");

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
