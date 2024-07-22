package model.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlaggedStateTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new BombedCell();
        cell.setState(new FlaggedState());
    }

    @Test
    void testStateAfterReveal() {
        cell.reveal();
        assertInstanceOf(FlaggedState.class, cell.getState());
    }

    @Test
    void testStateAfterToggleFlag() {
        cell.toggleFlag();
        assertInstanceOf(ClosedState.class, cell.getState());
    }
}