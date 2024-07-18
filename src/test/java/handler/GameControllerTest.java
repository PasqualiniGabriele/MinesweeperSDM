package handler;

import cli.CLIHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;
    private CLIHandler handler;


    @BeforeEach
    public void setUp() {
        handler = new CLIHandler();
        gameController = new GameController(handler);
    }

    private void setInput(String userInput) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        handler.setScanner(new Scanner(testIn));
    }

    @Test
    public void testNoExceptionLaunch() {
        setInput("1\nC 1 1\nQ");
        assertDoesNotThrow(() -> {
            gameController.launch();
        });
    }
}

