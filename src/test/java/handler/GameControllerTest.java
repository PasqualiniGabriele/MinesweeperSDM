package handler;

import model.Difficulty;
import model.GameStatus;
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
        assertEquals(Difficulty.EASY, controller.getGame().getDifficulty());
    }

    @Test
    void testCreateMediumGame() {
        controller.createGame("MEDIUM");
        assertEquals(Difficulty.MEDIUM, controller.getGame().getDifficulty());
    }

    @Test
    void testCreateHardGame() {
        controller.createGame("HARD");
        assertEquals(Difficulty.HARD, controller.getGame().getDifficulty());
    }

    @Test
    void testEndGameWithStatusWon() {
        controller.createGame("MEDIUM");
        controller.endGame("WON");
        assertEquals(GameStatus.WON, controller.getGame().getStatus());
    }

    @Test
    void testEndGameWithStatusLost() {
        controller.createGame("MEDIUM");
        controller.endGame("LOST");
        assertEquals(GameStatus.LOST, controller.getGame().getStatus());
    }
}
