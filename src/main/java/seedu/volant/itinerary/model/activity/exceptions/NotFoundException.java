package seedu.volant.itinerary.model.activity.exceptions;

/**
 * Represents a parse error encountered by a parser.
 */
public class NotFoundException extends NullPointerException {

    public NotFoundException(String message) {
        super(message);
    }

}
