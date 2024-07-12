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

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        cliHandler = new CLIHandler();
        gameController = mock(GameController.class);
        cliHandler.setGameController(gameController);
    }

    private void setInput(String userInput) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        cliHandler.setScanner(new Scanner(testIn));
    }

    @Test
    public void testExitFromLaunch() {
        setInput("3");

        cliHandler.launch();
    }

    @Test
    public void testNewGameAndExitFlow() {
        setInput("1\n1\nQ");

        cliHandler.launch();
    }

    @Test
    public void testGameRulesAndExitFlow() {
        setInput("2\np\n3");

        cliHandler.launch();
    }


    @Test
    public void testEasyDifficulty() {
        setInput("1\nQ");

        cliHandler.setDifficulty();

        verify(gameController).createGame("EASY");
    }

    @Test
    public void testMediumDifficulty() {
        setInput("2\nQ");

        cliHandler.setDifficulty();

        verify(gameController).createGame("MEDIUM");
    }

    @Test
    public void testHardDifficulty() {
        setInput("3\nQ");

        cliHandler.setDifficulty();

        verify(gameController).createGame("HARD");
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

}