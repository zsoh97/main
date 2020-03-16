package seedu.volant.ui.pages.journal;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.volant.commons.core.LogsCenter;
import seedu.volant.journal.model.entry.Entry;
import seedu.volant.journal.model.entry.UniqueEntryList;
import seedu.volant.trip.model.Journal;
import seedu.volant.ui.UiPart;
import seedu.volant.ui.pages.home.HomePage;


/**
 * Page containing details of a Trip as well as a menu of TripFeatures.
 */
public class JournalPage extends UiPart<Region> {
    private static final String FXML = "journal/JournalPage.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    @FXML
    private Label journalTitle;

    // TODO: Complete Itinerary page once Itinerary has been fully implemented.

    @FXML
    private ListView<Entry> entryListView;

    public JournalPage(Journal journal) {
        super(FXML);

        // Get entry list from journal
        UniqueEntryList entryList = journal.getEntryList().getUniqueEntryList();

        journalTitle.setText("Journal");

        entryListView.setItems(entryList.asUnmodifiableObservableList());
        entryListView.setCellFactory(listView -> new EntryListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of each {@code TripFeature} using a card depending on the
     * type of feature.
     */
    class EntryListViewCell extends ListCell<Entry> {
        @Override
        protected void updateItem(Entry entry, boolean empty) {
            super.updateItem(entry, empty);
            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JournalPageCard(entry).getRoot());
            }
        }
    }
}
