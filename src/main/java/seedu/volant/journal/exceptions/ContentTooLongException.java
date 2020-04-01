package seedu.volant.journal.exceptions;

import seedu.volant.commons.logic.parser.exceptions.ParseException;

public class ContentTooLongException extends ParseException {
    private int contentOverLimit;
    public ContentTooLongException(int contentOverLimit) {
        super("I'm sorry, journal entry content has to be 280 characters or less :( \n" +
                "You are currently " + contentOverLimit + " characters over the limit.");
    }
}
