package seedu.volant.journal.model;

import static seedu.volant.commons.util.StringUtil.formatDate;
import static seedu.volant.commons.util.StringUtil.formatTime;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.feeling.Feeling;

/**
 * Represents an Entry in a journal.
 */
public class Entry {

    private String text;
    private LocalDate date;
    private LocalTime time;
    private Feeling feeling;
    private Location location;

    /**
     * Constructs journal entry.
     * Date and time are automatically initialised to the date and time at which the user creates the journal entry.
     */
    public Entry(String text, String location) {
        // Initialises entry with current date and time when entry is created.
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.location = new Location(location);
        this.text = text;
    }

    /**
     * Constructs journal entry with feeling metadata specified.
     * Date and time are automatically initialised to the date and time at which the user creates the journal entry.
     */
    public Entry(String text, Feeling feeling) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.text = text;
        this.feeling = feeling;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return formatTime(time);
    }

    public String getDate() {
        return formatDate(date);
    }

    public String getLocation() {
        return location.toString();
    }

    public Feeling getFeeling() {
        return feeling;
    }

    @Override
    public String toString() {
        return getText();
    }
}
