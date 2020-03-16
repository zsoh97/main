package seedu.volant.commons.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.ReadOnlyTripList;

/**
 * API of the Storage component
 */
public interface Storage extends VolantStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getVolantFilePath();

    @Override
    Optional<ReadOnlyTripList> readTripList() throws DataConversionException, IOException;

    @Override
    void saveTripList(ReadOnlyTripList addressBook) throws IOException;

}
