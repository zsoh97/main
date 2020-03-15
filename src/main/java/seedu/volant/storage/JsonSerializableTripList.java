package seedu.volant.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.volant.commons.exceptions.IllegalValueException;
import seedu.volant.home.model.ReadOnlyTripList;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;


/**
 * An Immutable TripList that is serializable to JSON format.
 */
@JsonRootName(value = "volant")
class JsonSerializableTripList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Trip list contains duplicate trip(s).";

    private final List<JsonAdaptedTrip> trips = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTripList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTripList(@JsonProperty("trips") List<JsonAdaptedTrip> trips) {
        this.trips.addAll(trips);
    }

    /**
     * Converts a given {@code ReadOnlyTripList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTripList}.
     */
    public JsonSerializableTripList(ReadOnlyTripList source) {
        trips.addAll(source.getTripList().stream().map(JsonAdaptedTrip::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TripList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TripList toModelType() throws IllegalValueException {
        TripList tripList = new TripList();
        for (JsonAdaptedTrip jsonAdaptedTrip : trips) {
            Trip trip = jsonAdaptedTrip.toModelType();
            if (tripList.hasTrip(trip)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            tripList.addTrip(trip);
        }
        return tripList;
    }

}
