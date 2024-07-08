package controller;

import model.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController();
    }

    @Test
    void testCreateEasyGame() {
        controller.createGame("EASY");
        assertEquals(controller.getGame().getDifficulty(), Difficulty.EASY);
    }

    @Test
    void testCreateMediumGame() {
        controller.createGame("MEDIUM");
        assertEquals(controller.getGame().getDifficulty(), Difficulty.MEDIUM);
    }

    @Test
    void testCreateHardGame() {
        controller.createGame("HARD");
        assertEquals(controller.getGame().getDifficulty(), Difficulty.HARD);
    }
}