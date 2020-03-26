package seedu.volant.ui.pages.journal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.volant.journal.model.entry.Entry;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePageTripCard;

/**
 * An UI component that displays information of an {@code Entry}.
 */
public class JournalPageCard extends UiPart<Region> {
    private static final String FXML = "journal/JournalPageCard.fxml";

    private Entry entry;

    @FXML
    private HBox entryPane;

    @FXML
    private Label entryTitle;

    @FXML
    private Label entryDate;

    @FXML
    private Label entryTime;

    @FXML
    private Label entryLocation;

    @FXML
    private Label entryFeeling;

    @FXML
    private Label entryWeather;

    /*
    @FXML
    private Label entryFeeling;
    */

    public JournalPageCard(Entry entry) {
        super(FXML);
        this.entry = entry;
        entryTitle.setText(entry.getText());
        entryDate.setText(entry.getDateAsString());
        entryTime.setText(entry.getTimeAsString());
        entryLocation.setText(entry.getLocationAsString());
        entryFeeling.setText(entry.getFeeling().toString());
        entryWeather.setText(entry.getWeather().toString());
    }

    public Entry getEntry() {
        return entry;
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
        JournalPageCard card = (JournalPageCard) other;
        return entry.equals(card.entry);
    }
}
