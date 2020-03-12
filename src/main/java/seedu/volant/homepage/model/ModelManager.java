package seedu.volant.homepage.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.commons.core.index.Index;
import seedu.volant.homepage.model.trip.Trip;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TripList tripList;
    private final UserPrefs userPrefs;
    private final FilteredList<Trip> filteredTrips;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTripList tripList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tripList, userPrefs);

        logger.fine("Initializing with trip list: " + tripList + " and user prefs " + userPrefs);

        this.tripList = new TripList(tripList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrips = new FilteredList<>(this.tripList.getTripList());
    }

    public ModelManager() {
        this(new TripList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getVolantFilePath() {
        return userPrefs.getVolantFilePath();
    }

    @Override
    public void setVolantFilePath(Path volantFilePath) {
        requireNonNull(volantFilePath);
        userPrefs.setVolantFilePath(volantFilePath);
    }

    //=========== TripList ================================================================================

    @Override
    public void setTripList(ReadOnlyTripList addressBook) {
        this.tripList.resetData(addressBook);
    }

    @Override
    public ReadOnlyTripList getTripList() {
        return tripList;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return tripList.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip target) {
        tripList.removeTrip(target);
    }

    @Override
    public Trip gotoTrip(Index targetIndex) {
        return tripList.gotoTrip(targetIndex.getZeroBased());
    }

    @Override
    public void addTrip(Trip trip) {
        tripList.addTrip(trip);
        updateFilteredTripList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        tripList.setTrip(target, editedTrip);
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Trip} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Trip> getFilteredTripList() {
        return filteredTrips;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        filteredTrips.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tripList.equals(other.tripList)
                && userPrefs.equals(other.userPrefs)
                && filteredTrips.equals(other.filteredTrips);
    }

}
