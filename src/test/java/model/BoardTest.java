package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private BoardManager boardManager;

    @BeforeEach
    void setUp() {
        boardManager = new BoardManager(Difficulty.EASY);
        board = boardManager.getBoard();
    }

    @Test
    void testGenerateRandomCoordinates_RightNumber() {
        Coordinate safeZoneCenter = new Coordinate(1, 1);
        boardManager.placeBombsAvoiding(safeZoneCenter);

        int bombCount = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(new Coordinate(x, y)) instanceof BombedCell) {
                    bombCount++;
                }
            }
        }
        assertEquals(Difficulty.EASY.getNumOfBombs(), bombCount);
    }

    @Test
    void testSafeZone() {
        Random random = new Random();
        int randX = random.nextInt(board.getWidth());
        int randY = random.nextInt(board.getHeight());
        Coordinate safeZoneCenter = new Coordinate(randX, randY);
        boardManager.placeBombsAvoiding(safeZoneCenter);

        boolean check = true;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int x = safeZoneCenter.x() + dx;
                int y = safeZoneCenter.y() + dy;
                if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight()) {
                    if (board.getCell(new Coordinate(x, y)) instanceof BombedCell) check = false;
                }
            }
        }
        assertTrue(check);
    }

    @Test
    void testRevealAdjacentArea() {
        BombedCell bomb = new BombedCell();
        board.setCell(bomb, new Coordinate(0, 1));
        boardManager.updateProximity(new Coordinate(0, 1));
        board.setCell(bomb, new Coordinate(1, 0));
        boardManager.updateProximity(new Coordinate(1, 0));
        board.setCell(bomb, new Coordinate(4, 2));
        boardManager.updateProximity(new Coordinate(4, 2));

        boardManager.revealAdjacentArea(new Coordinate(2, 2));
        int[][] expectedOpenCells =
        {{0, 0, 1, 1},
         {0, 1, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 0, 1}};

        int[][] actualOpenCells = new int[5][4];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getCell(new Coordinate(i, j)).getState() instanceof OpenState) {
                    actualOpenCells[i][j] = 1;
                } else {
                    actualOpenCells[i][j] = 0;
                }
            }
        }
        boolean areEqual = Arrays.deepEquals(actualOpenCells, expectedOpenCells);
        assertTrue(areEqual);
    }
}
