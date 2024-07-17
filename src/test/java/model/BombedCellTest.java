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
        Cell flaggedCell = new BombedCell();
        flaggedCell.setState(new FlaggedState());
        assertEquals("⚑", flaggedCell.toString());
    }
    @Test
    void testClosedBombedCell(){
        Cell closedCell = new BombedCell();
        closedCell.setState(new ClosedState());
        assertEquals(" ", closedCell.toString());
    }
    @Test
    void testOpenBombedCell(){
        Cell OpenCell = new BombedCell();
        OpenCell.setState(new OpenState());
        assertEquals("✷", OpenCell.toString());
    }
}