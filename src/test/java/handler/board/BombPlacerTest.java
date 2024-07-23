package handler.board;

import model.board.Board;
import model.cell.BombedCell;
import model.board.Configuration;
import model.board.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BombPlacerTest {

    private Board board;
    private Board mockBoard;
    private BombPlacer bombPlacer;

    @BeforeEach
    void setUp() {
        Configuration config = Configuration.EASY;
        mockBoard = mock(Board.class);
        board = new Board(config);
        when(mockBoard.getWidth()).thenReturn(config.getWidth());
        when(mockBoard.getHeight()).thenReturn(config.getHeight());
        when(mockBoard.getNumOfBombs()).thenReturn(config.getNumOfBombs());
    }

    @Test
    void testGenerateBombCoordinates_RightNumber() {
        bombPlacer = new BombPlacer(board);
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
        bombPlacer = new BombPlacer(board);
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

    @Test
    void testPlaceBombsAvoiding_NumberOfIterations(){
        bombPlacer = new BombPlacer(mockBoard);
        Coordinate coordinate = new Coordinate(1, 1);
        bombPlacer.placeBombsAvoiding(coordinate);
        verify(mockBoard, times(10)).setCell(any(BombedCell.class), any(Coordinate.class));
    }

}