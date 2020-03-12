package seedu.volant.ui.pages.homepage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.homepage.model.trip.Trip;
import seedu.volant.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class TripListPanel extends UiPart<Region> {
    private static final String FXML = "TripListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TripListPanel.class);

    @FXML
    private ListView<Trip> tripListView;

    public TripListPanel(ObservableList<Trip> tripList) {
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
                setGraphic(new TripListCard(trip, getIndex() + 1).getRoot());
            }
        }
    }

}
