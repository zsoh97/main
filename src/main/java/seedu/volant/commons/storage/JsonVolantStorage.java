package seedu.volant.commons.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.exceptions.DataConversionException;
import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.commons.util.FileUtil;
import seedu.volant.commons.util.JsonUtil;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.storage.JsonSerializableTripList;

/**
 * A class to access TripList data stored as a json file on the hard disk.
 */
public class JsonVolantStorage implements VolantStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonVolantStorage.class);

    private Path filePath;

    public JsonVolantStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getVolantFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTripList> readTripList() throws DataConversionException {
        return readTripList(filePath);
    }

    /**
     * Similar to {@link #readTripList()} ()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTripList> readTripList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTripList> jsonTripList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTripList.class);
        if (!jsonTripList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTripList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTripList(ReadOnlyTripList tripList) throws IOException {
        saveTripList(tripList, filePath);
    }

    /**
     * Similar to {@link #saveTripList(ReadOnlyTripList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTripList(ReadOnlyTripList tripList, Path filePath) throws IOException {
        requireNonNull(tripList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTripList(tripList), filePath);
    }

}
