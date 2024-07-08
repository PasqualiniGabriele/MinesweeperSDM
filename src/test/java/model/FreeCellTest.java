package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreeCellTest {
    Cell cell;

    @Test
    void testIfCellHasMine(){
        cell = new FreeCell();
        assertFalse(cell.hasMine());
    }
}
