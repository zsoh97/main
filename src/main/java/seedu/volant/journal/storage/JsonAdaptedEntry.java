package seedu.volant.journal.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.trip.Location;
import seedu.volant.journal.model.entry.Entry;
import seedu.volant.journal.model.entry.Feeling;
import seedu.volant.journal.model.entry.Weather;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    private String text;
    private String date;
    private String time;
    private String feeling;
    private String weather;
    private String location;

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        text = source.getText();
        date = source.getDate();
        time = source.getTime();
        feeling = source.getFeeling().name();
        weather = source.getWeather().name();
        location = source.getLocation();
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        if (text == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Text"));
        }
        final String modelText = text;

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }
        DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd MMM yyyy");
        final LocalDate modelDate = LocalDate.parse(date, dateParser);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Time"));
        }
        DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm a");
        final LocalTime modelTime = LocalTime.parse(time, timeParser);

        if (feeling == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Feeling"));
        }
        final Feeling modelFeeling = Feeling.valueOf(feeling);

        if (weather == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Weather"));
        }
        final Weather modelWeather = Weather.valueOf(weather);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Location"));
        }
        final Location modelLocation = new Location(location);

        Entry entry = new Entry(modelDate, modelTime);
        entry.setLocation(modelLocation);
        entry.setFeeling(modelFeeling);
        entry.setWeather(modelWeather);
        entry.setText(modelText);
        return entry;
    }
}
