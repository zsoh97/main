package seedu.volant.journal.model.entry;

/**
 * Represents a state of Feeling in a journal Entry.
 */
public enum Feeling {
    NULL, HAPPY, SAD, EXCITED, WORRIED, SCARED, SURPRISED, CONFUSED;
    public static final String MESSAGE_CONSTRAINTS = "That feeling cannot be felt";
}
