package seedu.volant.home.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.volant.home.logic.commands.CommandTestUtil.DESC_CNY;
import static seedu.volant.home.logic.commands.CommandTestUtil.DESC_GRADTRIP;

import org.junit.jupiter.api.Test;

public class EditTripDescriptorTest {

    // TODO: Refine these tests
    @Test
    public void equals() {
        // same values -> returns true
        EditTripDescriptorTest descriptorWithSameValues = new EditTripDescriptorTest();
        assertTrue(DESC_GRADTRIP.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GRADTRIP.equals(DESC_GRADTRIP));

        // null -> returns false
        assertFalse(DESC_GRADTRIP.equals(null));

        // different types -> returns false
        assertFalse(DESC_GRADTRIP.equals(5));

        // different values -> returns false
        assertFalse(DESC_GRADTRIP.equals(DESC_CNY));

    }
}
