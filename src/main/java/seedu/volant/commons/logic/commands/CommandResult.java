package seedu.volant.commons.logic.commands;

import java.util.Objects;

import seedu.volant.commons.logic.Page;
import seedu.volant.homepage.model.trip.Trip;

import static seedu.volant.commons.logic.Page.TRIP;
import static java.util.Objects.requireNonNull;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    protected final String feedbackToUser;

    /** Help information should be shown to the user. */
    protected final boolean showHelp;

    /** The application should exit. */
    protected final boolean exit;

    /** The application should switch pages. */
    protected boolean gotoPage;

    protected Trip trip;

    protected Page page;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean gotoPage) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.gotoPage = gotoPage;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public CommandResult(String feedbackToUser, Trip trip) {
        this(feedbackToUser, false, false, true);
        this.trip = trip;
        this.page = TRIP;
    }

    public CommandResult(String feedbackToUser, Page page) {
        this(feedbackToUser, false, false, true);
        this.trip = null;
        this.page = page;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isGoto() {
        return gotoPage;
    }

    public Trip getTrip() {
        return trip;
    }

    public Page getPage() {
        return page;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, gotoPage);
    }

}
