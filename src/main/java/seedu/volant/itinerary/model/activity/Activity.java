package seedu.volant.itinerary.model.activity;

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

    public Activity(String title, Location location, LocalDate date, LocalTime time) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
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
        StringBuilder sb = new StringBuilder();
        sb.append(title)
                .append("\nLocation: ")
                .append(location)
                .append("\nDate: ")
                .append(date)
                .append("\nTime: ")
                .append(time);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other == this) {
            result = true;
        }

        if (other instanceof Activity) {
            Activity otherActivity = (Activity) other;
            if ((this.getDate().equals(otherActivity.getDate()))
                    && (this.getTime().equals(otherActivity.getTime()))) {
                result = true;
            }
        }
        return result;
    }
}
