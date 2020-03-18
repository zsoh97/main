package seedu.volant.itinerary.model.activity;

import static seedu.volant.commons.util.StringUtil.formatDate;
import static seedu.volant.commons.util.StringUtil.formatTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("\nLocation: ")
                .append(this.getTitle())
                .append("\nDate: ")
                .append(this.getDate())
                .append("\nTime: ")
                .append(this.getTime());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        boolean result;
        if (other == this) {
            result = true;
        }

        if (other instanceof Activity) {
            result = true;
        }

        Activity otherActivity = (Activity) other;
        if ((this.getDate().equals(((Activity) other).getDate()))
            && (this.getTime().equals(((Activity) other).getTime()))) {
            result = true;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.date, this.time, this.location);
    }
}
