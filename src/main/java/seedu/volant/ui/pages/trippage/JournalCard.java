package seedu.volant.ui.pages.trippage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.volant.trippage.model.TripFeature;
import seedu.volant.ui.UiPart;

/**
 * An UI component that displays information of a {@code Journal}.
 */
public class JournalCard extends UiPart<Region> {

    private static final String FXML = "JournalCard.fxml";

    @FXML
    private HBox journalPane;
    @FXML
    private Label journalTitle;

    public JournalCard(TripFeature journal) {
        super(FXML);
        journalTitle.setText("Journal");
    }

}
