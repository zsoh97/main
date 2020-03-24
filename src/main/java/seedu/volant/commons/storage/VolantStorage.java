package seedu.volant.commons.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.itinerary.model.ReadOnlyActivityList;
import seedu.volant.journal.model.ReadOnlyEntryList;

/**
 * Represents a storage for {@link TripList}.
 */
public interface VolantStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getVolantFilePath();

    void setVolantFilePath(Path newPath);

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

    /**
     * Returns AcitivityList data as a {@link ReadOnlyActivityList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyActivityList> readActivityList() throws DataConversionException, IOException;

    /**
     * @see #getVolantFilePath()
     */
    Optional<ReadOnlyActivityList> readActivityList(Path filePath) throws DataConversionException, IOException;
    /**
     * saves given {@link ReadOnlyActivityList} to the storage.
     * @param activityList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveActivityList(ReadOnlyActivityList activityList) throws IOException;

    /**
     * @see #saveActivityList(ReadOnlyActivityList)
     */
    void saveActivityList(ReadOnlyActivityList activityList, Path filePath) throws IOException;

    /**
     * Returns EntryList data as a {@link ReadOnlyEntryList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEntryList> readEntryList() throws DataConversionException, IOException;

    /**
     * @see #getVolantFilePath()
     */
    Optional<ReadOnlyEntryList> readEntryList(Path filePath) throws DataConversionException, IOException;
    /**
     * saves given {@link ReadOnlyEntryList} to the storage.
     * @param entryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEntryList(ReadOnlyEntryList entryList) throws IOException;

    /**
     * @see #saveActivityList(ReadOnlyActivityList)
     */
    void saveEntryList(ReadOnlyEntryList entryList, Path filePath) throws IOException;

}
