package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class TripDetails extends UiPart<Region> {

    private static final String FXML = "TripDetails.fxml";

    @FXML
    private TextArea tripDetails;

    public TripDetails() {
        super(FXML);
    }

    public void setTrip() {
        tripDetails.setText("GRAD TRIP, Bangkok, Thailand\n "
                    + "01/05/2020 - 15/05/2020");
    }

}
