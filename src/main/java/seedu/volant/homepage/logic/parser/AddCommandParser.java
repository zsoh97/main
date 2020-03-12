package seedu.volant.homepage.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.volant.commons.logic.parser.ArgumentMultimap;
import seedu.volant.commons.logic.parser.ArgumentTokenizer;
import seedu.volant.commons.logic.parser.Parser;
import seedu.volant.commons.logic.parser.ParserUtil;
import seedu.volant.commons.logic.parser.Prefix;
import seedu.volant.homepage.logic.commands.AddCommand;
import seedu.volant.homepage.logic.parser.exceptions.ParseException;
import seedu.volant.homepage.model.trip.DateRange;
import seedu.volant.homepage.model.trip.Location;
import seedu.volant.homepage.model.trip.Name;
import seedu.volant.homepage.model.trip.Trip;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LOCATION, PREFIX_DATERANGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_DATERANGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        DateRange dateRange = ParserUtil.parseDateRange(argMultimap.getAllValues(PREFIX_DATERANGE).get(0));

        Trip trip = new Trip(name, location, dateRange);

        return new AddCommand(trip);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
