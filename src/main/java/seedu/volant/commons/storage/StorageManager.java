package seedu.volant.commons.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.ReadOnlyTripList;

/**
 * Manages storage of TripList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private VolantStorage volantStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(VolantStorage volantStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.volantStorage = volantStorage;
        this.userPrefsStorage = userPrefsStorage;
    }


    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TripList methods ==============================

    @Override
    public Path getVolantFilePath() {
        return volantStorage.getVolantFilePath();
    }

    @Override
    public Optional<ReadOnlyTripList> readTripList() throws DataConversionException, IOException {
        return readTripList(volantStorage.getVolantFilePath());
    }

    @Override
    public Optional<ReadOnlyTripList> readTripList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return volantStorage.readTripList(filePath);
    }

    @Override
    public void saveTripList(ReadOnlyTripList tripList) throws IOException {
        saveTripList(tripList, volantStorage.getVolantFilePath());
    }

    @Override
    public void saveTripList(ReadOnlyTripList tripList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        volantStorage.saveTripList(tripList, filePath);
    }

}
