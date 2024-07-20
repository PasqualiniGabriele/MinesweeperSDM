package cli;

import handler.Command;
import handler.GameCommand;
import model.Coordinate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    public void testParseCommandWithValidInputs() {
        assertValidCommand("F 10 20", new GameCommand("F", new Coordinate(9, 19)));
        assertValidCommand("C 1 2", new GameCommand("C", new Coordinate(0, 1)));
    }

    @Test
    void testParseCommandWithInvalidInputs() {
        assertInvalidCommand("A 0 2");
        assertInvalidCommand("F -3 -4");
        assertInvalidCommand("C 1");
        assertInvalidCommand("C 1 2 3");
        assertInvalidCommand("F ten twenty");
    }

    private void assertValidCommand(String input, GameCommand expected) {
        GameCommand actual = (GameCommand) CommandParser.parseCommand(input);
        assertEquals(actual.getAction(), expected.getAction());
        assertEquals(actual.getCoordinate(), expected.getCoordinate());
    }

    private void assertInvalidCommand(String input) {
        assertThrows(IllegalArgumentException.class, () ->
                CommandParser.parseCommand(input)
        );
    }
}