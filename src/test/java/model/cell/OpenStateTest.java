package model.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenStateTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new BombedCell();
        cell.setState(new OpenState());
    }

    @Test
    void testStateAfterReveal() {
        cell.reveal();
        assertInstanceOf(OpenState.class, cell.getState());
    }

    @Test
    void testStateAfterToggleFlag() {
        cell.toggleFlag();
        assertInstanceOf(OpenState.class, cell.getState());
    }

}