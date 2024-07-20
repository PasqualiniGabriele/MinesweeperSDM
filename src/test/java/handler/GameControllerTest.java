package handler;

import cli.CLIHandler;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;
    private CLIHandler handler;
    private BoardManager mockBoardManager;


    @BeforeEach
    public void setUp() {
        handler = new CLIHandler();
        gameController = new GameController(handler);
        mockBoardManager = mock(BoardManager.class);
    }

    private void setInput(String userInput) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        handler.setScanner(new Scanner(testIn));
    }

    @Test
    public void testNoExceptionLaunch() {
        setInput("1\nC 1 1\nQ");
        assertDoesNotThrow(() -> gameController.launch());
    }

    @Test
    public void testCreateGameCall() {
        setInput("1\nC 1 1\nQ");
        gameController.launch();
        assertNotNull(gameController.getGame());
        assertNotNull(gameController.getBoardManager());
    }

    @Test
    public void testApplyCommandFlag() {
        gameController.setBoardManager(mockBoardManager);
        Coordinate testCoordinate = new Coordinate(1, 1);
        Command testCommand = new Command("F", testCoordinate);
        gameController.applyCommand(testCommand);
        verify(mockBoardManager).applyFlag(testCoordinate);
    }

    @Test
    public void testApplyCommandClick() {
        gameController.setBoardManager(mockBoardManager);
        Coordinate testCoordinate = new Coordinate(1, 1);
        Command testCommand = new Command("C", testCoordinate);
        gameController.applyCommand(testCommand);
        verify(mockBoardManager).applyClick(testCoordinate);
    }

    @Test
    public void testGameStats_Easy_BeginGame() {
        gameController.createGame(Configuration.EASY);
        String[] gameStats = gameController.getGameStats();
        assertEquals("EASY", gameStats[0]);
        assertEquals("10", gameStats[1]);
        assertEquals("0", gameStats[2]);
    }

    @Test
    public void testGameStats_Medium_AfterSeconds() throws InterruptedException {
        gameController.createGame(Configuration.MEDIUM);
        sleep(3000);
        String[] gameStats = gameController.getGameStats();
        assertEquals("MEDIUM", gameStats[0]);
        assertEquals("40", gameStats[1]);
        assertEquals("3", gameStats[2]);
    }
}

