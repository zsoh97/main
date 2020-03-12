package seedu.volant.ui.pages.homepage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.ui.UiPart;

/**
 * An UI component that displays information of a {@code Trip}.
 */
public class TripListCard extends UiPart<Region> {

    private static final String FXML = "TripListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TripList level 4</a>
     */

    public final Trip trip;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label place;
    @FXML
    private Label dateRange;

    public TripListCard(Trip trip, int displayedIndex) {
        super(FXML);
        this.trip = trip;
        id.setText(displayedIndex + ". ");
        name.setText(trip.getName().tripName);
        place.setText(trip.getLocation().toString());
        dateRange.setText(trip.getDateRange().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TripListCard)) {
            return false;
        }

        // state check
        TripListCard card = (TripListCard) other;
        return id.getText().equals(card.id.getText())
                && trip.equals(card.trip);
    }
}
