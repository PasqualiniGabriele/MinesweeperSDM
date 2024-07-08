package cli;

import model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    @Test
    public void testFlaggingCommandActionParsing() {
        String input = "F 1 2";
        Command expected = new Command("F", new Coordinate(1, 2));
        Command actual = CommandParser.parseCommand(input);
        assertEquals(expected, actual);
    }
}