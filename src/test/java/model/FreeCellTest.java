package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FreeCellTest {
    Cell cell;

    @Test
    void testIfCellHasMine(){
        cell = new FreeCell(0);
        assertFalse(cell.hasMine());
    }
    @Test
    void testFlaggedFreeCell(){
        CellState mockFlaggedState = mock(FlaggedState.class);
        Cell flaggedCell = new FreeCell(1);
        flaggedCell.setState(mockFlaggedState);
        assertEquals("🚩", flaggedCell.toString());
    }
    @Test
    void testClosedFreeCell(){
        CellState mockClosedState = mock(ClosedState.class);
        Cell closedCell = new FreeCell(1);
        closedCell.setState(mockClosedState);
        assertEquals(" ", closedCell.toString());
    }
    @Test
    void testOpenFreeCell(){
        CellState mockOpenState = mock(OpenState.class);
        Cell OpenCell = new FreeCell(1);
        OpenCell.setState(mockOpenState);
        assertEquals("1", OpenCell.toString());
    }
}
