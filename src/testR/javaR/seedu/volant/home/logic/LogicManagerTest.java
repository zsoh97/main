package seedu.volant.home.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX;
import static seedu.volant.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.volant.home.logic.commands.CommandTestUtil.DATERANGE_DESC_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.LOCATION_DESC_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.NAME_DESC_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATEFROM_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_DATETO_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_LOCATION_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.VALID_TRIPNAME_CNY;
import static seedu.volant.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.commons.storage.JsonUserPrefsStorage;
import seedu.volant.commons.storage.JsonVolantStorage;
import seedu.volant.commons.storage.StorageManager;
import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.logic.commands.ListCommand;
import seedu.volant.home.model.HomeModelManager;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.testutil.TripBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new HomeModelManager();
    private HomeLogicManager logic;

    @BeforeEach
    public void setUp() {
        JsonVolantStorage volantStorage =
                new JsonVolantStorage(temporaryFolder.resolve("volant.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(volantStorage, userPrefsStorage);
        logic = new HomeLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup HomeLogicManager with JsonVolantIoExceptionThrowingStub
        JsonVolantStorage volantStorage =
                new JsonVolantIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionvolant.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(volantStorage, userPrefsStorage);
        logic = new HomeLogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_CNY + LOCATION_DESC_CNY + DATERANGE_DESC_CNY;
        Trip expectedTrip = new TripBuilder().withName(VALID_TRIPNAME_CNY).withLocation(VALID_LOCATION_CNY)
                .withDateRange(VALID_DATEFROM_CNY, VALID_DATETO_CNY).build();
        HomeModelManager expectedModel = new HomeModelManager();
        expectedModel.addTrip(expectedTrip);
        String expectedMessage = HomeLogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTripList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new HomeModelManager(model.getTripList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonVolantIoExceptionThrowingStub extends JsonVolantStorage {
        private JsonVolantIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTripList(ReadOnlyTripList tripList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
