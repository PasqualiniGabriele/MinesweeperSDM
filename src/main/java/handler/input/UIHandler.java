package handler.input;

import model.board.Board;
import model.board.Configuration;
import model.game.Game;

/**
 * The {@code UIHandler} abstract class defining the interface for handling user input and rendering UI components.
 */
public abstract class UIHandler {

    /**
     * Constructs a new {@code UIHandler} instance.
     */
    public UIHandler() {
    }

    /**
     * Displays the game rules to the user.
     */
    public abstract void gameRules();

    /**
     * Handles the game exit process.
     *
     * @param status The final status of the game, indicating whether it was won, lost, or otherwise.
     */
    public abstract void exit(Game.Status status);

    /**
     * Prompts the user to provide a game configuration.
     *
     * @return The configuration chosen by the user.
     */
    public abstract Configuration askConfigurationToUser();

    /**
     * Renders the welcome screen for the user.
     */
    public abstract void renderWelcomeScreen();

    /**
     * Checks if there is a command ready to be processed.
     *
     * @return The next command from the user, or {@code null} if no command is available.
     */
    public abstract Command hasNextCommand();

    /**
     * Renders the game screen to the user.
     *
     * @param gameStats An array of strings containing the current game statistics.
     * @param board The current game board to be displayed.
     */
    public abstract void renderGameScreen(String[] gameStats, Board board);

    /**
     * Checks if the user has requested a new game.
     *
     * @return {@code true} if a new game has been requested, {@code false} otherwise.
     */
    public abstract boolean isNewGameRequested();
}
