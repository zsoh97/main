package seedu.volant.trippage.model;

import static seedu.volant.trippage.model.Feature.ITINERARY;

/**
 * Represents an itinerary.
 */
public class Itinerary extends TripFeature {
    public static final Feature FEATURE = ITINERARY;

    /**
     * Constructs itinerary object.
     */
    public Itinerary() {

    }

    public Feature getFeature() {
        return FEATURE;
    }
}
