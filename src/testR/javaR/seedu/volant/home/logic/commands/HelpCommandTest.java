package seedu.volant.home.logic.commands;

import static seedu.volant.commons.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.HelpCommand;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;

public class HelpCommandTest {
    private Model model = new HomeModelManager();
    private Model expectedModel = new HomeModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
