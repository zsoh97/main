package seedu.volant.homepage.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.parser.Parser;
import seedu.volant.commons.logic.parser.ParserUtil;
import seedu.volant.homepage.logic.commands.DeleteCommand;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
