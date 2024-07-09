package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4, 4);
    }

    @Test
    void testUpdateProximity() {
        boolean wrongProximity = false;
        board.setCell(new BombedCell(), new Coordinate(1, 1));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (x == 1 && y == 1) continue;
                FreeCell freeCell = (FreeCell) (board.getCell(new Coordinate(x, y)));
                if (freeCell.getProximity() != 1) wrongProximity = true;
            }
        }
        assertFalse(wrongProximity);

    }
}