package seedu.volant.home.model.trip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents date range of a trip.
 */
public class DateRange {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static final String MESSAGE_CONSTRAINTS = "Date range must be in \"YYYY-MM-DD to YYYY-MM-DD\" format!!";
    public static final String VALIDATION_REGEX = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|"
            + "(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))( to )[0-9]{4}-(((0[13578]|(10|12))-"
            + "(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

    public final String value;

    protected LocalDate from;
    protected LocalDate to;

    public DateRange(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
        value = from.getYear() + "-"
                + String.format("%02d", from.getMonthValue()) + "-"
                + String.format("%02d", from.getDayOfMonth()) + " to "
                + to.getYear() + "-"
                + String.format("%02d", to.getMonthValue()) + "-"
                + String.format("%02d", to.getDayOfMonth());
    }

    public static boolean isValidDateRange(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from.format(DATE_TIME_FORMATTER) + " - " + to.format(DATE_TIME_FORMATTER);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateRange)
                && value.equals(value); // state check
    }

}
