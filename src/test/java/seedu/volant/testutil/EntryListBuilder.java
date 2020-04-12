package seedu.volant.testutil;

import seedu.volant.journal.model.Entry;
import seedu.volant.journal.model.EntryList;

public class EntryListBuilder {
    private EntryList entryList;

    public EntryListBuilder() {
        entryList = new EntryList();
    }

    public EntryListBuilder withEntry(Entry entry) {
        entryList.addEntry(entry);
        return this;
    }

    public EntryList build() {
        return entryList;
    }
}
