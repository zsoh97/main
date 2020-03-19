package seedu.volant.trip.model;

import static seedu.volant.trip.model.Feature.JOURNAL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.entry.Entry;

/**
 * Represents a journal
 */
public class Journal extends TripFeature {
    public static final Feature FEATURE = JOURNAL;

    private String title;
    private EntryList entryList;

    /**
     * Constructs journal with no title.
     */
    public Journal() {
        // Sample entries w/ no metadata
        ArrayList<Entry> ass = new ArrayList<>();
        ass.add(new Entry(LocalDate.of(2020, 1, 1), LocalTime.of(8, 0)));
        ass.add(new Entry(LocalDate.of(2020, 2, 2), LocalTime.of(9, 15)));
        ass.add(new Entry(LocalDate.of(2020, 3, 3), LocalTime.of(10, 30)));
        ass.add(new Entry(LocalDate.of(2020, 4, 4), LocalTime.of(11, 45)));
        EntryList h = new EntryList();
        h.setActivities(ass);
        this.entryList = h;

        // this.entryList = new EntryList();
    }

    /**
     * Constructs journal with title {@param title}.
     */
    public Journal(String title) {
        this.title = title;
    }

    /**
     * Constructs journal with the given {@param entryList}.
     */
    public Journal(EntryList entryList) {
        this.entryList = entryList;
    }

    public EntryList getEntryList() {
        return entryList;
    }

    public Feature getFeature() {
        return FEATURE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryList // instanceof handles nulls
                && entryList.equals(((EntryList) other).getEntryList()));
    }

    @Override
    public int hashCode() {
        return entryList.hashCode();
    }
}
