package cli;

import handler.input.Command;
import handler.input.GameCommand;
import model.board.Coordinate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static handler.input.Command.Action.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @BeforeEach
    void setUp() {
        CommandParser.setMaxCoordinate(10, 10);
    }

    @Test
    public void testParseCommandWithValidInputs() {
        assertValidGameCommand("f 9 9", new GameCommand(FLAG_ACTION, new Coordinate(8, 8)));
        assertValidGameCommand("c 1 2", new GameCommand(CLICK_ACTION, new Coordinate(0, 1)));
        assertValidCommand("q", new Command(QUIT_ACTION));
        assertValidCommand("i", new Command(INFO_ACTION));
    }

    @Test
    void testParseCommandWithInvalidInputs() {
        assertInvalidCommand("A 0 2");
        assertInvalidCommand("F -3 -4");
        assertInvalidCommand("C 1");
        assertInvalidCommand("C 1 2 3");
        assertInvalidCommand("F ten twenty");
        assertInvalidCommand("F");
        assertInvalidCommand("c 20 20");
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