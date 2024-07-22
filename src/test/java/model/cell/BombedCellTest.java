package model.cell;

import org.junit.jupiter.api.Test;

import static cli.ConsoleColor.removeAnsiCodes;
import static org.junit.jupiter.api.Assertions.*;

class BombedCellTest {
    Cell cell;

    @Test
    void testIfCellHasMine() {
        cell = new BombedCell();
        assertTrue(cell.hasMine());
    }

    @Test
    void testFlaggedBombedCell() {
        Cell flaggedCell = new BombedCell();
        flaggedCell.setState(new FlaggedState());
        assertEquals("⚑", removeAnsiCodes(flaggedCell.toString()));
    }

    @Test
    void testClosedBombedCell() {
        Cell closedCell = new BombedCell();
        closedCell.setState(new ClosedState());
        assertEquals("■", removeAnsiCodes(closedCell.toString()));
    }

    @Test
    void testOpenBombedCell() {
        Cell OpenCell = new BombedCell();
        OpenCell.setState(new OpenState());
        assertEquals("●", removeAnsiCodes(OpenCell.toString()));
    }
}