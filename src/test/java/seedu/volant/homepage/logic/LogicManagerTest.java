package seedu.volant.homepage.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX;
import static seedu.volant.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.volant.commons.logic.Logic;
import seedu.volant.commons.logic.LogicManager;
import seedu.volant.homepage.logic.commands.AddCommand;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.logic.commands.ListCommand;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.ModelManager;
import seedu.volant.homepage.model.ReadOnlyTripList;
import seedu.volant.homepage.model.UserPrefs;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.storage.JsonVolantStorage;
import seedu.volant.storage.JsonUserPrefsStorage;
import seedu.volant.storage.StorageManager;
import seedu.volant.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonVolantStorage addressBookStorage =
                new JsonVolantStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
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
        // Setup LogicManager with JsonVolantIoExceptionThrowingStub
        JsonVolantStorage addressBookStorage =
                new JsonVolantIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Trip expectedTrip = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTrip(expectedTrip);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
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
        Model expectedModel = new ModelManager(model.getTripList(), new UserPrefs());
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
