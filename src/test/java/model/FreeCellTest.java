package model;

import org.junit.jupiter.api.Test;

import static cli.ConsoleColor.removeAnsiCodes;
import static org.junit.jupiter.api.Assertions.*;

class FreeCellTest {
    Cell cell;

    @Test
    void testIfCellHasMine(){
        cell = new FreeCell(0);
        assertFalse(cell.hasMine());
    }
    @Test
    void testFlaggedFreeCell(){
        Cell flaggedCell = new FreeCell(1);
        flaggedCell.setState(new FlaggedState());
        assertEquals("⚑", removeAnsiCodes(flaggedCell.toString()));
    }
    @Test
    void testClosedFreeCell(){
        Cell closedCell = new FreeCell(1);
        closedCell.setState(new ClosedState());
        assertEquals("■", removeAnsiCodes(closedCell.toString()));
    }
    @Test
    void testOpenFreeCell(){
        Cell OpenCell = new FreeCell(1);
        OpenCell.setState(new OpenState());
        assertEquals("1", removeAnsiCodes(OpenCell.toString()));
    }
}
