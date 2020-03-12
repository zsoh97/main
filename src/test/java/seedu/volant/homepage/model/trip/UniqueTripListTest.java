package seedu.volant.homepage.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.volant.homepage.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalPersons.ALICE;
import static seedu.volant.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.volant.homepage.model.trip.exceptions.DuplicateTripException;
import seedu.volant.homepage.model.trip.exceptions.PersonNotFoundException;
import seedu.volant.testutil.PersonBuilder;

public class UniqueTripListTest {

    private final UniqueTripList uniqueTripList = new UniqueTripList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueTripList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTripList.add(ALICE);
        assertTrue(uniqueTripList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTripList.add(ALICE);
        Trip editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTripList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTripList.add(ALICE);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueTripList.setTrip(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueTripList.add(ALICE);
        uniqueTripList.setTrip(ALICE, ALICE);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(ALICE);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueTripList.add(ALICE);
        Trip editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTripList.setTrip(ALICE, editedAlice);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(editedAlice);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueTripList.add(ALICE);
        uniqueTripList.setTrip(ALICE, BOB);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(BOB);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueTripList.add(ALICE);
        uniqueTripList.add(BOB);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrip(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueTripList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTripList.add(ALICE);
        uniqueTripList.remove(ALICE);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((UniqueTripList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueTripList.add(ALICE);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(BOB);
        uniqueTripList.setTrips(expectedUniqueTripList);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((List<Trip>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueTripList.add(ALICE);
        List<Trip> tripList = Collections.singletonList(BOB);
        uniqueTripList.setTrips(tripList);
        UniqueTripList expectedUniqueTripList = new UniqueTripList();
        expectedUniqueTripList.add(BOB);
        assertEquals(expectedUniqueTripList, uniqueTripList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Trip> listWithDuplicateTrips = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrips(listWithDuplicateTrips));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTripList.asUnmodifiableObservableList().remove(0));
    }
}
