package seedu.volant.testutil;

import seedu.volant.homepage.model.TripList;
import seedu.volant.homepage.model.trip.Trip;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TripList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TripList addressBook;

    public AddressBookBuilder() {
        addressBook = new TripList();
    }

    public AddressBookBuilder(TripList addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Trip} to the {@code TripList} that we are building.
     */
    public AddressBookBuilder withPerson(Trip trip) {
        addressBook.addPerson(trip);
        return this;
    }

    public TripList build() {
        return addressBook;
    }
}
