package seedu.volant.trippage.model;

import static seedu.volant.trippage.model.Feature.JOURNAL;

/**
 * Represents a journal
 */
public class Journal extends TripFeature {
    public static final Feature FEATURE = JOURNAL;

    /**
     * Constructs journal object.
     */
    public Journal() {

    }

    public Feature getFeature() {
        return FEATURE;
    }
}
