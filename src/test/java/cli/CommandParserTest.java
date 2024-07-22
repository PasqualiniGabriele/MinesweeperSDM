package cli;

import handler.Command;
import handler.GameCommand;
import model.Coordinate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    public void testParseCommandWithValidInputs() {
        assertValidGameCommand("F 10 20", new GameCommand("F", new Coordinate(9, 19)));
        assertValidGameCommand("C 1 2", new GameCommand("C", new Coordinate(0, 1)));
        assertValidCommand("Q", new Command("Q"));
    }

    @Test
    void testParseCommandWithInvalidInputs() {
        assertInvalidCommand("A 0 2");
        assertInvalidCommand("F -3 -4");
        assertInvalidCommand("C 1");
        assertInvalidCommand("C 1 2 3");
        assertInvalidCommand("F ten twenty");
        assertInvalidCommand("F");
    }

    private void assertValidCommand(String input, Command expected) {
        Command actual = CommandParser.parseCommand(input);
        assertEquals(actual.getAction(), expected.getAction());
    }

    private void assertValidGameCommand(String input, GameCommand expected) {
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