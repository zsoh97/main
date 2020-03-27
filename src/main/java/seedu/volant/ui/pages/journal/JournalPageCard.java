package seedu.volant.ui.pages.journal;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
        entryDate.setText(entry.getDate());
        entryTime.setText(entry.getTime());
        entryLocation.setText(entry.getLocation());
        entryWeather.setText(entry.getWeather().toString());
        setFeeling(entry.getFeeling().toString());
    }

    private void setFeeling(String feeling) {
        entryFeeling.setText(feeling);
        switch(feeling) {
        case "CONFUSED":
            entryFeeling.setBackground(new Background(new BackgroundFill(
                    Color.rgb(204, 195, 234), CornerRadii.EMPTY, Insets.EMPTY)));
            break;
        case "TIRED":
            entryFeeling.setBackground(new Background(new BackgroundFill(
                    Color.rgb(47, 233, 167), CornerRadii.EMPTY, Insets.EMPTY)));
            break;
        case "HAPPY":
            entryFeeling.setBackground(new Background(new BackgroundFill(
                    Color.rgb(240, 128, 128), CornerRadii.EMPTY, Insets.EMPTY)));
            break;
        default:
            break;
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
