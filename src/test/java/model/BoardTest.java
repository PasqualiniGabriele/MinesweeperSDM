package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4, 4);
    }

    @Test
    void testGenerateRandomCoordinates_RightNumber() {
        Coordinate safeZoneCenter = new Coordinate(1, 1);
        board.fillWithBombs(4, safeZoneCenter);

        int bombCount = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(new Coordinate(x, y)) instanceof BombedCell) {
                    bombCount++;
                }
            }
        }
        assertEquals(4, bombCount);
    }

    @Test
    void testSafeZone() {
        Random random = new Random();
        int randX = random.nextInt(board.getWidth());
        int randY = random.nextInt(board.getHeight());
        Coordinate safeZoneCenter = new Coordinate(randX, randY);
        board.fillWithBombs(2, safeZoneCenter);

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
