package seedu.volant.commons.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.volant.commons.core.index.Index;
import seedu.volant.homepage.logic.commands.GotoTripCommand;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GotoCommand object
 */
public class GotoCommandParser implements Parser<GotoTripCommand> {

    @Override
    public GotoTripCommand parse(String tripIndex) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(tripIndex);
            return new GotoTripCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
              String.format(MESSAGE_INVALID_COMMAND_FORMAT, GotoTripCommand.MESSAGE_USAGE, pe)
            );
        }
    }
}
