package seedu.volant.journal.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.util.SortType;
import seedu.volant.trip.model.Journal;

import java.nio.file.Path;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.testutil.Assert.assertThrows;
import static seedu.volant.testutil.TypicalTrips.getIndiaTrip;


public class AddCommandTest {
    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    private class ModelStub extends JournalModelManager {
        @Override
        public Page getPage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Trip getTrip() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Journal getJournal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public EntryList getEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntry(Entry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editEntry(Entry entry, Entry editedEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEntries(SortType sortType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Entry> getPredicateShowAllEntries() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntryList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean equals(Object obj) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getVolantFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVolantFilePath(Path volantFilePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithSingleEntry extends ModelStub {
        private final Trip trip;

        public ModelStubWithSingleEntry(Trip trip) {
            this.trip = trip;
        }

    }
}