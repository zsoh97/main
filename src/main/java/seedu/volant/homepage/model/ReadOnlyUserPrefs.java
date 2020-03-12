package seedu.volant.homepage.model;

import java.nio.file.Path;

import seedu.volant.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getVolantFilePath();

}
