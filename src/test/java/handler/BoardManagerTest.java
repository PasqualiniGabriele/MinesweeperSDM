package handler;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    private Board board;
    private BoardManager boardManager;

    @BeforeEach
    void setUp() {
        boardManager = new BoardManager(Configuration.EASY);
        board = boardManager.getBoard();
    }

    @Test
    void testRevealAdjacentArea() {

    }
}
