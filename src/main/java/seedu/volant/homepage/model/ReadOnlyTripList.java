package seedu.volant.homepage.model;

import javafx.collections.ObservableList;

import seedu.volant.homepage.model.trip.Trip;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTripList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Trip> getTripList();

}
