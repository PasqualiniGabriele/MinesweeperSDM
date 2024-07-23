package handler.game;

import cli.CLIHandler;
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

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;
    private CLIHandler handler;
    private BoardManager mockBoardManager;


    private GameController gameController1;
    private UIHandler mockHandler;
    private Game game;
    private BoardManager mockBoardManager1;


    @BeforeEach
    public void setUp() {
        handler = new CLIHandler();
        gameController = new GameController(handler);
        mockBoardManager = mock(BoardManager.class);

        mockHandler = mock(UIHandler.class);
        gameController1 = new GameController(mockHandler);
        gameController1.createGame(Configuration.EASY);
        game = mock(Game.class);
        mockBoardManager1 = mock(BoardManager.class);
        gameController1.boardManager = mockBoardManager1;
        gameController1.game = game;
    }


    @Test
    void testLaunch(){
        when(mockHandler.isNewGameRequested()).thenReturn(false);
        when(mockHandler.askForConfiguration()).thenReturn(Configuration.EASY);
        when(mockHandler.hasNextCommand()).thenReturn(new Command(Command.QUIT_ACTION));
        gameController1.launch();
        verify(mockHandler, times(1)).welcome();
        verify(mockHandler, times(1)).askForConfiguration();
    }

    @Test
    public void testApplyCommandFlag() {
        gameController.setBoardManager(mockBoardManager);
        Coordinate testCoordinate = new Coordinate(1, 1);
        GameCommand testCommand = new GameCommand("F", testCoordinate);
        gameController.applyCommand(testCommand);
        verify(mockBoardManager).applyFlag(testCoordinate);
    }

    @Test
    public void testApplyCommandClick() {
        gameController.setBoardManager(mockBoardManager);
        Coordinate testCoordinate = new Coordinate(1, 1);
        GameCommand testCommand = new GameCommand("C", testCoordinate);
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

    @Test
    public void testWinGame() {
        gameController.createGame(Configuration.EASY);
        gameController.applyCommand(new GameCommand("C", new Coordinate(5, 5)));
        Board board = gameController.getBoardManager().getBoard();
        winGame(board);
        assertEquals(0, gameController.getBoardManager().getFreeCellsLeft());
    }

    private void winGame(Board board) {
        while (gameController.getGame().getStatus() != GameStatus.WON) {
            Random random = new Random();
            int x = random.nextInt(board.getWidth());
            int y = random.nextInt(board.getHeight());
            Coordinate coordinate = new Coordinate(x, y);
            if (board.getCell(coordinate) instanceof FreeCell)
                gameController.applyCommand(new GameCommand("C", coordinate));
        }
    }
}

