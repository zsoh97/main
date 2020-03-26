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

    @FXML
    private Label id;

    public JournalPageCard(Entry entry, int displayedIndex) {
        super(FXML);
        this.entry = entry;
        id.setText(displayedIndex + ". ");
        entryTitle.setText(entry.getText());
        entryDate.setText(entry.getDate() + ",");
        entryTime.setText(entry.getTime());
        if (entry.getLocation().equals("null")) {
            entryLocation.setText("\nLocation: Not specified");
        } else {
            entryLocation.setText("\nLocation: " + entry.getLocation());
        }
        if (entry.getFeeling().toString().equals("NULL")) {
            entryFeeling.setText("Feeling: Not specified");
        } else {
            String feeling = entry.getFeeling().toString();
            entryFeeling.setText("Feeling: " + feeling.substring(0, 1) + feeling.substring(1).toLowerCase());
        }
        if (entry.getWeather().toString().equals("NULL")) {
            entryWeather.setText("Weather: Not specified");
        } else {
            String weather = entry.getWeather().toString();
            entryWeather.setText("Weather: " + weather.substring(0, 1) + weather.substring(1).toLowerCase());
        }
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
