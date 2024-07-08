package cli;

import model.Coordinate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    public void testParseCommandWithValidInputs() {
        assertValidCommand("F 10 20", new Command("F", new Coordinate(10, 20)));
        assertValidCommand("C 1 2", new Command("C", new Coordinate(1, 2)));
    }

    @Test
    void testParseCommandWithInvalidInputs() {
        assertInvalidCommand("A 2 2");
        assertInvalidCommand("F -3 -4");
        assertInvalidCommand("C 1");
        assertInvalidCommand("C 1 2 3");
        assertInvalidCommand("F ten twenty");
    }

    private void assertValidCommand(String input, Command expected) {
        Command actual = CommandParser.parseCommand(input);
        assertEquals(expected, actual);
    }

    private void assertInvalidCommand(String input) {
        assertThrows(IllegalArgumentException.class, () ->
                CommandParser.parseCommand(input)
        );
    }
}