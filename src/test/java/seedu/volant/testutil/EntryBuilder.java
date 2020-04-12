package seedu.volant.testutil;

import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.Feeling;
import seedu.volant.journal.model.Weather;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntryBuilder {
    public static final String DEFAULT_DATE = "2020-04-02";
    public static final String DEFAULT_TIME = "12:51";
    public static final String DEFAULT_TEXT = "I am testing out Volant's journal!";
    public static final String DEFAULT_FEELING = "EXCITED";
    public static final String DEFAULT_WEATHER = "SUNNY";
    public static final String DEFAULT_LOCATION = "Tembusu College";

    private String text;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private Feeling feeling;
    private Weather weather;

    public EntryBuilder() {
        text = DEFAULT_TEXT;
        location = DEFAULT_LOCATION;
        date = LocalDate.parse(DEFAULT_DATE);
        time = LocalTime.parse(DEFAULT_TIME);
        feeling = Feeling.valueOf(DEFAULT_FEELING);
        weather = Weather.valueOf(DEFAULT_WEATHER);
    }

    public EntryBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public EntryBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public EntryBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    public EntryBuilder withTime(String time) {
        this.time = LocalTime.parse(time);
        return this;
    }

    public EntryBuilder withFeeling(String feeling) {
        this.feeling = Feeling.valueOf(feeling);
        return this;
    }

    public EntryBuilder withWeather(String weather) {
        this.weather = Weather.valueOf(weather);
        return this;
    }

    public Entry build() {
        Entry entry = new Entry(date, time, text, feeling, weather, location);
        return entry;
    }
}
