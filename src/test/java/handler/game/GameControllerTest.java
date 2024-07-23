package handler.game;

import handler.board.BoardManager;
import handler.input.Command;
import handler.input.GameCommand;
import handler.input.UIHandler;
import model.board.Board;
import model.board.Configuration;
import model.board.Coordinate;
import model.cell.FreeCell;
import model.game.Game;
import model.game.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static handler.input.Command.Action.*;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;
    private UIHandler handler;
    private BoardManager boardManager;
    private Game game;


    @BeforeEach
    public void setUp() {
        handler = mock(UIHandler.class);
        gameController = new GameController(handler);
        gameController.createGame(Configuration.EASY);
        game = mock(Game.class);
        boardManager = mock(BoardManager.class);
        gameController.boardManager = boardManager;
        gameController.game = game;
    }


    @Test
    void testLaunch() {
        when(handler.isNewGameRequested()).thenReturn(false);
        when(handler.askConfigurationToUser()).thenReturn(Configuration.EASY);
        when(handler.hasNextCommand()).thenReturn(new Command(QUIT_ACTION));
        gameController.launch();
        verify(handler, times(1)).renderWelcomeScreen();
        verify(handler, times(1)).askConfigurationToUser();
    }

    @Test
    void testCreateGame() {
        gameController.createGame(Configuration.EASY);
        assertNotNull(gameController.game);
        assertNotNull(gameController.boardManager);
    }

    @Test
    void testGameLoop() {
        when(game.getStatus()).thenReturn(GameStatus.ONGOING).thenReturn(GameStatus.LOST);
        when(handler.hasNextCommand()).thenReturn(new Command(FLAG_ACTION));
        gameController.gameLoop();
        verify(handler, times(1)).hasNextCommand();
        verify(game, times(2)).getStatus();
    }

    @Test
    public void testApplyCommandFlag() {
        Coordinate testCoordinate = new Coordinate(1, 1);
        GameCommand testCommand = new GameCommand(FLAG_ACTION, testCoordinate);
        gameController.applyCommand(testCommand);
        verify(boardManager).applyFlag(testCoordinate);
    }

    @Test
    public void testApplyCommandClick() {
        Coordinate testCoordinate = new Coordinate(1, 1);
        GameCommand testCommand = new GameCommand(CLICK_ACTION, testCoordinate);
        gameController.applyCommand(testCommand);
        verify(boardManager).applyClick(testCoordinate);
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

    @Test
    public void testWinGame() {
        gameController.createGame(Configuration.EASY);
        gameController.applyCommand(new GameCommand(CLICK_ACTION, new Coordinate(5, 5)));
        Board board = gameController.boardManager.getBoard();
        winGame(board);
        assertEquals(0, gameController.boardManager.getFreeCellsLeft());
    }

    private void winGame(Board board) {
        while (gameController.game.getStatus() != GameStatus.WON) {
            Random random = new Random();
            int x = random.nextInt(board.getWidth());
            int y = random.nextInt(board.getHeight());
            Coordinate coordinate = new Coordinate(x, y);
            if (board.getCell(coordinate) instanceof FreeCell)
                gameController.applyCommand(new GameCommand(CLICK_ACTION, coordinate));
        }
    }

    @Test
    void testOnBombReveal() {
        gameController.onBombReveal();
        verify(game, times(1)).setEndStatus(GameStatus.LOST);
        verify(handler, times(1)).exit(GameStatus.LOST);
    }

}

