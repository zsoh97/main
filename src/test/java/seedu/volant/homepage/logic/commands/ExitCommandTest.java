package seedu.volant.homepage.logic.commands;

import static seedu.volant.homepage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.volant.commons.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.homepage.model.Model;
import seedu.volant.homepage.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
