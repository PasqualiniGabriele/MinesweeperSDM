package handler.game;

import handler.board.BoardManager;
import handler.input.Command;
import handler.input.GameCommand;
import handler.input.UIHandler;
import model.board.Configuration;
import model.board.Coordinate;
import model.game.Game;

import static handler.input.Command.Action.*;
import static model.game.Game.Status.*;
import static handler.input.Command.*;

/**
 * The {@code GameController} class manages the game flow, including setting up a new game, processing commands,
 * and handling game events. It interacts with the {@code UIHandler} for user interactions and the {@code BoardManager}
 * for board operations.
 */

public class GameController implements GameEventListener {

    protected Game game;
    protected BoardManager boardManager;
    private final UIHandler handler;
    private final CommandProcessor commandProcessor;

    /**
     * Constructs a {@code GameController} with the specified {@code UIHandler}.
     *
     * @param handler the {@code UIHandler} used for rendering game information and handling user input
     */

    public GameController(UIHandler handler) {
        this.handler = handler;
        commandProcessor = new CommandProcessor();
    }

    /**
     * Launches the game by displaying the welcome screen and runs the game loop.
     */

    public void launch() {
        handler.renderWelcomeScreen();
        do {
            Configuration configuration = handler.askConfigurationToUser();
            createGame(configuration);
            gameLoop();
            GameEventManager.getInstance().unsubscribeAll();
        } while (handler.isNewGameRequested());
    }

    /**
     * Creates a new game with the specified configuration and initializes the board manager.
     * Subscribes this {@code GameController} to game events.
     *
     * @param configuration the {@code Configuration} object containing game setup parameters
     */

    public void createGame(Configuration configuration) {
        game = new Game();
        boardManager = new BoardManager(configuration);
        GameEventManager.getInstance().subscribe(this);
    }

    /**
     * Runs the main game loop. Continues as long as the game's status is ongoing.
     */

    public void gameLoop() {
        while (game.getStatus() == ONGOING) {
            handler.renderGameScreen(getGameStats(), boardManager.getBoard());
            Command command = handler.hasNextCommand();
            applyCommand(command);
        }
    }

    /**
     * Applies the given {@code Command} by delegating to the {@code CommandProcessor}.
     *
     * @param command the {@code Command} to be applied
     */

    public void applyCommand(Command command) {
        commandProcessor.applyCommand(command);
    }

    /**
     * Ends the game with the specified status, and opens all cells on the board.
     *
     * @param endStatus the {@code GameStatus} indicating how the game ended (e.g., WON or LOST)
     */

    public void endGame(Game.Status endStatus) {
        game.setEndStatus(endStatus);
        handler.exit(endStatus);
        boardManager.openAllCells();
        handler.renderGameScreen(getGameStats(), boardManager.getBoard());
    }

    /**
     * Retrieves the current game statistics including board configuration, flags left, and game time.
     *
     * @return an array of {@code String} containing game statistics
     */

    public String[] getGameStats() {
        String[] stats = new String[3];
        stats[0] = String.valueOf(boardManager.getConfiguration());
        stats[1] = String.valueOf(boardManager.getFlagsLeft());
        stats[2] = String.valueOf(game.calculateGameTime());
        return stats;
    }

    /**
     * Called when a bomb is revealed. Ends the game with a LOST status.
     */

    @Override
    public void onBombReveal() {
        endGame(LOST);
    }

    /**
     * Called when a free cell is revealed. Decrements the count of free cells and ends the game with a WON status
     * if there are no free cells left.
     */

    @Override
    public void onFreeCellReveal() {
        boardManager.decrementFreeCellsLeft();
        if (boardManager.getFreeCellsLeft() == 0) {
            endGame(WON);
        }
    }

    /**
     * The {@code CommandProcessor} class processes commands issued by the user, distinguishing between game-related
     * commands and menu commands.
     */

    public class CommandProcessor {

        /**
         * Processes the given {@code Command}. Determines whether the command is a game-related command or a menu
         * command and delegates to the appropriate method.
         *
         * @param command the {@code Command} to process
         */

        public void applyCommand(Command command) {
            if (command instanceof GameCommand gameCommand) {
                applyGameCommand(gameCommand.getAction(), gameCommand.getCoordinate());
            } else {
                applyMenuCommand(command.getAction());
            }
        }

        /**
         * Processes a game-related command by applying the specified action to the given coordinate.
         *
         * @param action     the {@code Action} to be applied
         * @param coordinate the {@code Coordinate} where the action should be applied
         */

        private void applyGameCommand(Action action, Coordinate coordinate) {
                switch (action) {
                    case FLAG_ACTION:
                        boardManager.applyFlag(coordinate);
                        break;
                    case CLICK_ACTION:
                        boardManager.applyClick(coordinate);
                        break;
                }
        }

        /**
         * Processes a menu-related command by applying the specified action.
         *
         * @param action the {@code Action} to be applied
         */

        private void applyMenuCommand(Action action) {
            switch (action) {
                case QUIT_ACTION:
                    game.setEndStatus(QUIT);
                    break;
                case INFO_ACTION:
                    handler.gameRules();
                    break;
                case EASTER_EGG_ACTION:
                    easterEgg();
                    break;
            }
        }

        /**
         * Executes the easter egg action, which ends the game with a WON status.
         */

        private void easterEgg() {
            if (!boardManager.isFirstClickMade())
                applyGameCommand(CLICK_ACTION, new Coordinate(1, 1));
            endGame(WON);
        }
    }

}