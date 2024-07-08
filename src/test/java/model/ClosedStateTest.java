package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosedStateTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new BombedCell();
        cell.setState(new ClosedState());
    }

    @Test
    void testStateAfterReveal(){
        cell.reveal();
        assertInstanceOf(OpenState.class, cell.getState());
    }

    @Test
    void testStateAfterToggleFlag(){
        cell.toggleFlag();
        assertInstanceOf(FlaggedState.class, cell.getState());
    }
}