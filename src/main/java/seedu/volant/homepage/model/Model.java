package seedu.volant.homepage.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.core.index.Index;
import seedu.volant.homepage.model.trip.Trip;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Trip> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getVolantFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setVolantFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setTripList(ReadOnlyTripList addressBook);

    /** Returns the TripList */
    ReadOnlyTripList getTripList();

    /**
     * Returns true if a trip with the same identity as {@code trip} exists in the address book.
     */
    boolean hasTrip(Trip trip);

    /**
     * Deletes the given trip.
     * The trip must exist in the address book.
     */
    void deleteTrip(Trip target);

    /**
     * Returns trip to move to.
     * @param targetIndex index to move to in trip list.
     * @return targeted trip.
     */
    Trip gotoTrip(Index targetIndex);

    /**
     * Adds the given trip.
     * {@code trip} must not already exist in the address book.
     */
    void addTrip(Trip trip);

    /**
     * Replaces the given trip {@code target} with {@code editedTrip}.
     * {@code target} must exist in the address book.
     * The trip identity of {@code editedTrip} must not be the same as another existing trip in the address book.
     */
    void setTrip(Trip target, Trip editedTrip);

    /** Returns an unmodifiable view of the filtered trip list */
    ObservableList<Trip> getFilteredTripList();

    /**
     * Updates the filter of the filtered trip list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTripList(Predicate<Trip> predicate);
}
