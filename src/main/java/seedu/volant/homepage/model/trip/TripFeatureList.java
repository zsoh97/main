package seedu.volant.homepage.model.trip;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.volant.trippage.model.Itinerary;
import seedu.volant.trippage.model.Journal;
import seedu.volant.trippage.model.TripFeature;

/**
 * A list containing the items in a trip.
 */
public class TripFeatureList implements Iterable<TripFeature> {
    private ArrayList<TripFeature> tripFeatureList;

    public TripFeatureList() {
        tripFeatureList = new ArrayList<>();
        tripFeatureList.add(new Journal());
        tripFeatureList.add(new Itinerary());
    }

    public ArrayList<TripFeature> getTripFeatureList() {
        return tripFeatureList;
    }

    public ObservableList<TripFeature> getReadOnly() {
        return FXCollections.observableArrayList(getTripFeatureList());
    }

    @Override
    public int hashCode() {
        return getTripFeatureList().hashCode();
    }

    @Override
    public Iterator<TripFeature> iterator() {
        return getTripFeatureList().iterator();
    }
}
