package seedu.volant.journal.model;

import javafx.collections.ObservableList;
import seedu.volant.journal.model.entry.Entry;

/**
 * Unmodifiable view of the activity list.
 */
public interface ReadOnlyEntryList {

    /**
     * Returns an unmodifiable view of the entire itinerary.
     */
    ObservableList<Entry> getEntryList();
}
