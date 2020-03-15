package seedu.volant.itinerary.model;

import static seedu.volant.commons.util.StringUtil.formatDate;
import static seedu.volant.commons.util.StringUtil.formatTime;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.volant.home.model.trip.Location;

/**
 * Represents an activity in an activity list.
 */
public class Activity {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Location location;

    public Activity(String title, LocalDate date, LocalTime time) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return title;
    }
}
