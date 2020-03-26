package seedu.volant.ui.pages.itinerary;

import static seedu.volant.commons.util.StringUtil.formatDate;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.itinerary.model.activity.Activity;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePageTripCard;

/**
 * An UI component that displays information of an {@code Activity}.
 */
public class ItineraryPageCard extends UiPart<Region> {
    private static final String FXML = "itinerary/ItineraryPageCard.fxml";

    private Activity activity;

    @FXML
    private HBox activityPane;

    @FXML
    private Label activityTitle;

    @FXML
    private Label activityTime;

    @FXML
    private Label activityDate;

    @FXML
    private Label activityLocation;


    public ItineraryPageCard(Activity activity) {
        super(FXML);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
        this.activity = activity;
        activityTitle.setText(activity.getTitle().toString());
        activityDate.setText(formatDate(activity.getDate()));
        activityTime.setText(activity.getTime().format(formatter));
        activityLocation.setText(activity.getLocation().toString());
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HomePageTripCard)) {
            return false;
        }

        // state check
        ItineraryPageCard card = (ItineraryPageCard) other;
        return activity.equals(card.activity);
    }
}
