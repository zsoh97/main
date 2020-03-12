package seedu.volant.homepage.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.homepage.logic.commands.exceptions.CommandException;
import seedu.volant.homepage.model.TripList;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.ReadOnlyTripList;
import seedu.volant.homepage.model.ReadOnlyUserPrefs;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Trip validTrip = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validTrip).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTrip), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTrip), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Trip validTrip = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validTrip);
        ModelStub modelStub = new ModelStubWithPerson(validTrip);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Trip alice = new PersonBuilder().withName("Alice").build();
        Trip bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different trip -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Trip trip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyTripList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTripList getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Trip trip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Trip target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Trip target, Trip editedTrip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Trip> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Trip> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single trip.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Trip trip;

        ModelStubWithPerson(Trip trip) {
            requireNonNull(trip);
            this.trip = trip;
        }

        @Override
        public boolean hasPerson(Trip trip) {
            requireNonNull(trip);
            return this.trip.isSamePerson(trip);
        }
    }

    /**
     * A Model stub that always accept the trip being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Trip> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Trip trip) {
            requireNonNull(trip);
            return personsAdded.stream().anyMatch(trip::isSamePerson);
        }

        @Override
        public void addPerson(Trip trip) {
            requireNonNull(trip);
            personsAdded.add(trip);
        }

        @Override
        public ReadOnlyTripList getAddressBook() {
            return new TripList();
        }
    }

}
