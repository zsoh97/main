package seedu.volant.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;

/**
 * Represents a storage for {@link TripList}.
 */
public interface VolantStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getVolantFilePath();

    /**
     * Returns TripList data as a {@link ReadOnlyTripList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTripList> readTripList() throws DataConversionException, IOException;

    /**
     * @see #getVolantFilePath()
     */
    Optional<ReadOnlyTripList> readTripList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTripList} to the storage.
     * @param tripList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTripList(ReadOnlyTripList tripList) throws IOException;

    /**
     * @see #saveTripList(ReadOnlyTripList)
     */
    void saveTripList(ReadOnlyTripList tripList, Path filePath) throws IOException;

}
