package handler;

import model.Board;
import model.BombedCell;
import model.Configuration;
import model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BombPlacerTest {

    private Board board;
    private BombPlacer bombPlacer;

    @BeforeEach
    void setUp() {
        BoardManager boardManager = new BoardManager(Configuration.EASY);
        board = boardManager.getBoard();
        bombPlacer = new BombPlacer(Configuration.EASY, board);
    }

    @Test
    void testGenerateRandomCoordinates_RightNumber() {
        Coordinate safeZoneCenter = new Coordinate(1, 1);
        bombPlacer.placeBombsAvoiding(safeZoneCenter);

        int bombCount = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(new Coordinate(x, y)) instanceof BombedCell) {
                    bombCount++;
                }
            }
        }
        assertEquals(Configuration.EASY.getNumOfBombs(), bombCount);
    }

    @Test
    void testSafeZone() {
        Random random = new Random();
        int randX = random.nextInt(board.getWidth());
        int randY = random.nextInt(board.getHeight());
        Coordinate safeZoneCenter = new Coordinate(randX, randY);
        bombPlacer.placeBombsAvoiding(safeZoneCenter);

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

}