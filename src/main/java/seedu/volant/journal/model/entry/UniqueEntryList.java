package seedu.volant.journal.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.journal.model.exceptions.EntryNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A trip is considered unique by comparing using {@code Entry#isSameEntry(Entry)}. As such, adding and updating of
 * persons uses Entry#isSameEntry(Entry) for equality so as to ensure that the trip being added or updated is
 * unique in terms of identity in the UniqueEntryList. However, the removal of a trip uses Entry#equals(Object) so
 * as to ensure that the trip with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueEntryList implements Iterable<Entry> {

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /* TODO: This class is adapted from UniqueTripList.
     * Implement all these methods once implementation of Journal is complete.
     */

    /**
     * Returns true if the list contains an equivalent trip as the given argument.
     *
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }
    */
    /**
     * Adds a trip to the list.
     * The trip must not already exist in the list.
     */
    public void add(Entry toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the trip {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * The trip identity of {@code editedEntry} must not be the same as another existing trip in the list.
     */
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }
        internalList.set(index, editedEntry);
    }

    /**
     * Removes the equivalent trip from the list.
     * The trip must exist in the list.
     */
    public void remove(Entry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */

    public void setEntries(List<Entry> activities) {
        requireAllNonNull(activities);

        /* TODO: Implement methods to check for duplicate/clashing activities
        if (!activitiesAreUnique(activities)) {
            throw new DuplicateEntryException();
        }
        */

        internalList.setAll(activities);
    }
    /*

    public void setEntries(UniqueEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

   */

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Entry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntryList // instanceof handles nulls
                && internalList.equals(((UniqueEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /*
     * Returns true if {@code activities} contains only unique activities.

    private boolean activitiesAreUnique(List<Entry> activities) {
        for (int i = 0; i < activities.size() - 1; i++) {
            for (int j = i + 1; j < activities.size(); j++) {
                if (activities.get(i).isSameEntry(activities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    */
}
