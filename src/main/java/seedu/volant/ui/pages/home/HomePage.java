package seedu.volant.ui.pages.home;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class HomePage extends UiPart<Region> {
    private static final String FXML = "home/HomePage.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    @FXML
    private ListView<Trip> tripListView;

    public HomePage(ObservableList<Trip> tripList) {
        super(FXML);
        tripListView.setItems(tripList);
        tripListView.setCellFactory(listView -> new TripListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Trip} using a {@code TripCard}.
     */
    class TripListViewCell extends ListCell<Trip> {
        @Override
        protected void updateItem(Trip trip, boolean empty) {
            super.updateItem(trip, empty);
            if (empty || trip == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HomePageTripCard(trip, getIndex() + 1).getRoot());
            }
        }
    }

}
