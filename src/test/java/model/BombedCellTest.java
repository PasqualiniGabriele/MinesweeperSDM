package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BombedCellTest {
    Cell cell;

    @Test
    void testIfCellHasMine(){
        cell = new BombedCell();
        assertTrue(cell.hasMine());
    }
    @Test
    void testFlaggedBombedCell(){
        CellState mockFlaggedState = mock(FlaggedState.class);
        Cell flaggedCell = new BombedCell();
        flaggedCell.setState(mockFlaggedState);
        assertEquals("🚩", flaggedCell.toString());
    }
    @Test
    void testClosedBombedCell(){
        CellState mockClosedState = mock(ClosedState.class);
        Cell closedCell = new BombedCell();
        closedCell.setState(mockClosedState);
        assertEquals(" ", closedCell.toString());
    }
    @Test
    void testOpenBombedCell(){
        CellState mockOpenState = mock(OpenState.class);
        Cell OpenCell = new BombedCell();
        OpenCell.setState(mockOpenState);
        assertEquals("💣", OpenCell.toString());
    }
}