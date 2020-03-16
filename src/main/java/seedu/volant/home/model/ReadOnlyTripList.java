package seedu.volant.home.model;

import javafx.collections.ObservableList;
import seedu.volant.home.model.trip.Trip;

/**
 * Unmodifiable view of the trip list.
 */
public interface ReadOnlyTripList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Trip> getTripList();

}
