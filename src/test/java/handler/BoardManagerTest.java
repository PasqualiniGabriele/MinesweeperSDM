package handler;

import cli.CLIHandler;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testRevealAdjacentArea() {
        setBombs(new Coordinate(0, 3), new Coordinate(3, 0),
                new Coordinate(6, 0), new Coordinate(7, 2));

        boardManager.revealAdjacentArea(new Coordinate(5, 5));
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
        int[][] actualOpenCells = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = board.getCell(new Coordinate(j, i));
                if (cell.isOpenCell()) {
                    actualOpenCells[i][j] = 1;
                } else {
                    actualOpenCells[i][j] = 0;
                }
            }
        }
        assertArrayEquals(expectedOpenCells, actualOpenCells);
    }

    @Test
    void testFreeCellsLeft() {
        bombPlacer.placeBombsAvoiding(new Coordinate(4, 4));
        Command command = new GameCommand("C", new Coordinate(4, 4));
        gameController.createGame(Configuration.EASY);
        gameController.applyCommand(command);
        int expectedFreeCellsLeft = boardManager.getFreeCellsLeft();
        int actualFreeCellsLeft = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getCell(new Coordinate(i, j)) instanceof FreeCell freeCell) {
                    if (freeCell.isClosedCell()) actualFreeCellsLeft++;
                }
            }
        }
        assertEquals(expectedFreeCellsLeft, actualFreeCellsLeft);
    }

    void setBombs(Coordinate... coordinates) {
        for (Coordinate coordinate : coordinates) {
            BombedCell bombedCell = new BombedCell();
            board.setCell(bombedCell, coordinate);
            bombPlacer.updateProximity(coordinate);
        }
    }
}
