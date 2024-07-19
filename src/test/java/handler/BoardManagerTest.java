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
        BombedCell bombedCell = new BombedCell();
        board.setCell(bombedCell, new Coordinate(0, 3));
        bombPlacer.updateProximity(new Coordinate(0, 3));
        board.setCell(bombedCell, new Coordinate(3, 0));
        bombPlacer.updateProximity(new Coordinate(3, 0));
        board.setCell(bombedCell, new Coordinate(6, 0));
        bombPlacer.updateProximity(new Coordinate(6, 0));
        board.setCell(bombedCell, new Coordinate(7, 2));
        bombPlacer.updateProximity(new Coordinate(7, 2));
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
        assertArrayEquals(expectedOpenCells,actualOpenCells);
    }
}
