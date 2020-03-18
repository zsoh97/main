package seedu.volant.itinerary.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.itinerary.model.activity.UniqueActivityList;

/**
 * Wraps all data at the itinerary level
 * Duplicates are not allowed (by .isSameActivity comparison)
 */
public class ActivityList implements ReadOnlyActivityList {

    private final UniqueActivityList activities;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        activities = new UniqueActivityList();
    }

    public ActivityList() {

    }

    /**
     * Creates an ActivityList using the activities in the {@code toBeCopied}
     */
    public ActivityList(ReadOnlyActivityList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the activity list with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(List<Activity> activities) {
        this.activities.setActivities(activities);
    }

    /**
     * Resets the existing data of this {@code ActivityList} with {@code newData}.
     */
    public void resetData(ReadOnlyActivityList newData) {
        requireNonNull(newData);

        setActivities(newData.getActivityList());
    }

    /* TODO: Complete all these methods after full implementation of Itinerary as the methods here are reliant on
     *  UniqueActivityList which can only be fully implemented after full implementation of Itinerary.

    */

    /**
     * Returns true if a activity with the same identity as {@code activity} exists in the activity list.
     * @param activity Activity to be added.
     * @return True if activity list contains activity
     */
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return activities.contains(activity);
    }

    /**
     * Adds a activity to the activity list.
     * The trip must not already exist in the address book.
     */

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    //public void setActivity(Activity target, Activity editedActivity) {

    /**
     * Removes {@code key} from this {@code ActivityList}.
     * {@code key} must exist in the address book.
     * @param key index of Activity in activity list to be removed.
     */
    public void removeActivity(Activity key) {
        activities.remove(key);
    }


    //// util methods


    @Override
    public String toString() {
        return activities.asUnmodifiableObservableList().size() + " asscheeks";
    }

    public UniqueActivityList getUniqueActivityList() {
        return activities;
    }

    @Override
    public ObservableList<Activity> getActivityList() {
        return activities.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityList // instanceof handles nulls
                && activities.equals(((ActivityList) other).activities));
    }

    @Override
    public int hashCode() {
        return activities.hashCode();
    }

}
