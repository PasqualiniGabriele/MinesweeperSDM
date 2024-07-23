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
        mockBoard = mock(Board.class);
        board = new Board(Configuration.EASY);
        when(mockBoard.getWidth()).thenReturn(8);
        when(mockBoard.getHeight()).thenReturn(8);
    }

    @Test
    void testGenerateBombCoordinates_RightNumber() {
        bombPlacer = new BombPlacer(Configuration.EASY, board);
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
        bombPlacer = new BombPlacer(Configuration.EASY, board);
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
        bombPlacer = new BombPlacer(Configuration.EASY, mockBoard);
        Coordinate coordinate = new Coordinate(1, 1);
        bombPlacer.placeBombsAvoiding(coordinate);
        verify(mockBoard, times(10)).setCell(any(BombedCell.class), any(Coordinate.class));
    }

}