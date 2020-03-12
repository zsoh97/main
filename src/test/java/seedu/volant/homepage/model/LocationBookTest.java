package seedu.volant.homepage.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalPersons.ALICE;
import static seedu.volant.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.homepage.model.trip.exceptions.DuplicateTripException;
import seedu.volant.testutil.PersonBuilder;

public class LocationBookTest {

    private final TripList addressBook = new TripList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TripList newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two trips with the same identity fields
        Trip editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Trip> newTrips = Arrays.asList(ALICE, editedAlice);
        TripListStub newData = new TripListStub(newTrips);

        assertThrows(DuplicateTripException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Trip editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTripList whose trips list can violate interface constraints.
     */
    private static class TripListStub implements ReadOnlyTripList {
        private final ObservableList<Trip> trips = FXCollections.observableArrayList();

        TripListStub(Collection<Trip> trips) {
            this.trips.setAll(trips);
        }

        @Override
        public ObservableList<Trip> getPersonList() {
            return trips;
        }
    }

}
