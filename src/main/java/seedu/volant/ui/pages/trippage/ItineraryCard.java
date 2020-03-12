package seedu.volant.ui.pages.trippage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.volant.trippage.model.TripFeature;
import seedu.volant.ui.UiPart;

/**
 * An UI component that displays information of a {@code Itinerary}.
 */
public class ItineraryCard extends UiPart<Region> {

    private static final String FXML = "ItineraryCard.fxml";

    @FXML
    private HBox itineraryPane;
    @FXML
    private Label itineraryTitle;

    public ItineraryCard(TripFeature itinerary) {
        super(FXML);
        itineraryTitle.setText("Itinerary");
    }
}
