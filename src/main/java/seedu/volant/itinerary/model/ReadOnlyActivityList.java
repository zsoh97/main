package seedu.volant.itinerary.model;

import javafx.collections.ObservableList;
import seedu.volant.itinerary.model.activity.Activity;

/**
 * Unmodifiable view of the activity list.
 */
public interface ReadOnlyActivityList {

    /**
     * Returns an unmodifiable view of the entire itinerary.
     */
    ObservableList<Activity> getActivityList();
}
