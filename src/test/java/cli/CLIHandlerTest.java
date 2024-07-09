package cli;

import controller.GameController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CLIHandlerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    private CLIHandler cliHandler;
    private GameController gameController;
    private CommandParser commandParser;
    private DisplayFormatter displayFormatter;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        gameController = mock(GameController.class);
        commandParser = mock(CommandParser.class);
        displayFormatter = mock(DisplayFormatter.class);

        cliHandler = new CLIHandler(gameController, commandParser, displayFormatter);
    }

    @Test
    public void testLaunch() {
        String userInput = "3";
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        cliHandler.setScanner(new Scanner(testIn));

        cliHandler.launch();

        String expectedOutput = """
                Welcome to Minesweeper!
                Menu:
                1. Start new game
                2. See game rules
                3. Exit game
                Choose an option:""";
        assertEquals(expectedOutput, outContent.toString().strip());
    }

    @Test
    public void testNewGame() {
        String userInput = "1";
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        cliHandler.setScanner(new Scanner(testIn));

        cliHandler.newGame();

        String expectedOutput = """
                Choose difficulty:
                1. Easy
                2. Medium
                3. Hard""";
        assertEquals(expectedOutput, outContent.toString().strip());
    }

    @Test
    public void testEasyNewGame() {
        String userInput = "1\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        cliHandler.setScanner(new Scanner(testIn));
        cliHandler.newGame();
        verify(gameController).createGame("EASY");
    }

    @Test
    public void testMediumNewGame() {
        String userInput = "2\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        cliHandler.setScanner(new Scanner(testIn));
        cliHandler.newGame();
        verify(gameController).createGame("MEDIUM");
    }

    @Test
    public void testHardNewGame() {
        String userInput = "3\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);
        cliHandler.setScanner(new Scanner(testIn));
        cliHandler.newGame();
        verify(gameController).createGame("HARD");
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

}