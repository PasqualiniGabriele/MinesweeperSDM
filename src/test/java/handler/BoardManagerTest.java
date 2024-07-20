package handler;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    private Board board;
    private BoardManager boardManager;
    private BombPlacer bombPlacer;

    @BeforeEach
    void setUp() {
        boardManager = new BoardManager(Configuration.EASY);
        board = boardManager.getBoard();
        bombPlacer = new BombPlacer(Configuration.EASY, board);
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

    void setBombs(Coordinate... coordinates) {
        for (Coordinate coordinate : coordinates) {
            BombedCell bombedCell = new BombedCell();
            board.setCell(bombedCell, coordinate);
            bombPlacer.updateProximity(coordinate);
        }
    }
}
