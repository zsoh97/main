package seedu.volant.homepage.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.volant.homepage.model.ReadOnlyTripList;
import seedu.volant.homepage.model.TripList;
import seedu.volant.homepage.model.trip.DateRange;
import seedu.volant.homepage.model.trip.Location;
import seedu.volant.homepage.model.trip.Name;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.homepage.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TripList} with sample data.
 */
public class SampleDataUtil {
    public static Trip[] getSampleTrips() {
        return new Trip[] {
            new Trip(new Name("Graduation Trip"), new Location("Bali, Indonesia"),
                     new DateRange(LocalDate.parse("2020-02-01"), LocalDate.parse("2020-02-05")))
        };
    }

    public static ReadOnlyTripList getSampleAddressBook() {
        TripList sampleAb = new TripList();
        for (Trip sampleTrip : getSampleTrips()) {
            sampleAb.addTrip(sampleTrip);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
