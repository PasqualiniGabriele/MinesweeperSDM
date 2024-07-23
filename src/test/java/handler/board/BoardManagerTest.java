package handler.board;

import cli.CLIHandler;
import handler.game.GameController;
import handler.input.Command;
import handler.input.GameCommand;
import model.board.Board;
import model.board.Configuration;
import model.board.Coordinate;
import model.cell.Cell;
import model.cell.FreeCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static handler.input.Command.Action.CLICK_ACTION;
import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {
    private GameController gameController;
    private Board board;
    private BoardManager boardManager;
    private BombPlacer bombPlacer;

    @BeforeEach
    void setUp() {
        boardManager = new BoardManager(Configuration.EASY);
        board = boardManager.getBoard();
        bombPlacer = new BombPlacer(Configuration.EASY, board);
        gameController = new GameController(new CLIHandler());
    }

    @Test
    void testInitialization() {
        Configuration config = Configuration.EASY;
        BoardManager manager = new BoardManager(config);

        assertEquals(config.getWidth() * config.getHeight() - config.getNumOfBombs(), manager.getFreeCellsLeft());
        assertEquals(config.getNumOfBombs(), manager.getFlagsLeft());
        assertFalse(manager.isFirstClickMade());
    }

    @Test
    void testRevealSurroundingCells() {
        Set<Coordinate> bombCoordinates = Set.of(new Coordinate(0, 3),
                                                 new Coordinate(3, 0),
                                                 new Coordinate(6, 0),
                                                 new Coordinate(7, 2));
        bombPlacer.placeBombs(bombCoordinates);

        boardManager.revealCell(new Coordinate(5, 5));

        int[][] expectedOpenCells = {
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

        int[][] actualOpenCells = getActualOpenCellsMatrix();

        assertArrayEquals(expectedOpenCells, actualOpenCells);
    }

    private int[][] getActualOpenCellsMatrix() {
        int[][] openCellsMatrix = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = board.getCell(new Coordinate(j, i));
                openCellsMatrix[i][j] = cell.isOpenCell() ? 1 : 0;
            }
        }
        return openCellsMatrix;
    }

    @Test
    void testFlagsLeftUpdate() {
        Coordinate coord = new Coordinate(0, 0);
        boardManager.applyFlag(coord);
        assertEquals(Configuration.EASY.getNumOfBombs() - 1, boardManager.getFlagsLeft());

        boardManager.applyFlag(coord);
        assertEquals(Configuration.EASY.getNumOfBombs(), boardManager.getFlagsLeft());
    }


    @Test
    void testFreeCellsLeft() {
        bombPlacer.placeBombsAvoiding(new Coordinate(4, 4));
        Command command = new GameCommand(CLICK_ACTION, new Coordinate(4, 4));
        gameController.createGame(Configuration.EASY);
        gameController.applyCommand(command);

        int expectedFreeCellsLeft = boardManager.getFreeCellsLeft();
        int actualFreeCellsLeft = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getCell(new Coordinate(i, j)) instanceof FreeCell freeCell && freeCell.isClosedCell()) {
                    actualFreeCellsLeft++;
                }
            }
        }
        assertEquals(expectedFreeCellsLeft, actualFreeCellsLeft);
    }
}
