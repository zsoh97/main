package seedu.volant.trip.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list containing the items in a trip.
 */
public class TripFeatureList implements Iterable<TripFeature> {
    private ArrayList<TripFeature> tripFeatureList = new ArrayList<>();

    private Journal journal;
    private Itinerary itinerary;

    public TripFeatureList(Itinerary itinerary, Journal journal) {
        this.journal = journal;
        this.itinerary = itinerary;
        tripFeatureList.add(journal);
        tripFeatureList.add(itinerary);
    }

    public TripFeatureList() {
        this.journal = new Journal();
        this.itinerary = new Itinerary();
        tripFeatureList.add(getJournal());
        tripFeatureList.add(getItinerary());
    }

    public List<TripFeature> getTripFeatureList() {
        return tripFeatureList;
    }

    /**
     * Returns read only feature list for display onto GUI.
     */
    public ObservableList<TripFeature> getReadOnlyFeatureList() {
        return FXCollections.observableArrayList(getTripFeatureList());
    }

    public Journal getJournal() {
        return journal;
    }

    public Itinerary getItinerary() {
        return itinerary;
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
