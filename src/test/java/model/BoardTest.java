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
        Coordinate bombCoordinate = new Coordinate(1, 1);
        board.setCell(new BombedCell(), bombCoordinate);
        board.updateProximity(bombCoordinate);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!(x == 1 && y == 1)) {
                    Coordinate c = new Coordinate(x, y);
                    if (((FreeCell) (board.getCell(c))).getProximity() != 1) {
                        wrongProximity = true;
                    }
                }
            }
        }
        assertFalse(wrongProximity);
    }
}