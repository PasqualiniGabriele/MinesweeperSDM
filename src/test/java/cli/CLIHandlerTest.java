package cli;

import model.board.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CLIHandlerTest {
    private CLIHandler cliHandler;

    @BeforeEach
    public void setUp() {
        cliHandler = new CLIHandler();
    }

    private void setInput(String userInput) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        cliHandler.scanner = new Scanner(testIn);
    }

    @Test
    void testAskConfigurationToUser() {
        setInput("1\n");
        Configuration configuration= cliHandler.askConfigurationToUser();
        assertEquals(Configuration.EASY, configuration);
    }

    @Test
    void testIsNewGameRequested() {
        setInput("y\n");
        assertTrue(cliHandler.isNewGameRequested());
        setInput("n\n");
        assertFalse(cliHandler.isNewGameRequested());
    }
}