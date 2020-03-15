package seedu.volant.home.logic.commands;

import static seedu.volant.commons.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.volant.home.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.commons.model.Model;
import seedu.volant.home.model.HomeModelManager;

public class ExitCommandTest {
    private Model model = new HomeModelManager();
    private Model expectedModel = new HomeModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
