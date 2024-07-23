package handler.game;

import handler.board.BoardManager;
import handler.input.Command;
import handler.input.GameCommand;
import handler.input.UIHandler;
import model.board.Configuration;
import model.board.Coordinate;
import model.game.Game;
import model.game.GameStatus;

import java.sql.SQLOutput;

import static model.game.GameStatus.*;
import static handler.input.Command.*;

public class GameController implements GameEventListener {

    protected Game game;
    protected BoardManager boardManager;
    private final UIHandler handler;
    private final CommandProcessor commandProcessor;

    public GameController(UIHandler handler) {
        this.handler = handler;
        commandProcessor = new CommandProcessor();
    }

    public void launch() {
        handler.welcome();
        do {
            Configuration configuration = handler.askForConfiguration();
            createGame(configuration);
            gameLoop();
            GameEventManager.getInstance().unsubscribeAll();
        } while (handler.isNewGameRequested());
    }

    public void createGame(Configuration configuration) {
        game = new Game();
        boardManager = new BoardManager(configuration);
        GameEventManager.getInstance().subscribe(this);
    }

    public void gameLoop() {
        while (game.getStatus() == ONGOING) {
            handler.show(getGameStats(), boardManager.getBoard());
            Command command = handler.hasNextCommand();
            applyCommand(command);
        }
    }

    public void applyCommand(Command command) {
        commandProcessor.applyCommand(command);
    }

    public void endGame(GameStatus endStatus) {
        game.end(endStatus);
        handler.exit(endStatus);
        boardManager.openAllCells();
        handler.show(getGameStats(), boardManager.getBoard());
    }

    public String[] getGameStats() {
        String[] stats = new String[3];
        stats[0] = String.valueOf(boardManager.getConfiguration());
        stats[1] = String.valueOf(boardManager.getFlagsLeft());
        stats[2] = String.valueOf(game.calculateGameTime());
        return stats;
    }

    @Override
    public void onBombReveal() {
        endGame(LOST);
    }

    @Override
    public void onFreeCellReveal() {
        boardManager.decrementFreeCellsLeft();
        if (boardManager.getFreeCellsLeft() == 0) {
            endGame(WON);
        }
    }

    public class CommandProcessor {

        public void applyCommand(Command command) {
            if (command instanceof GameCommand gameCommand) {
                applyGameCommand(gameCommand.getAction(), gameCommand.getCoordinate());
            } else {
                applyMenuCommand(command.getAction());
            }
        }

        private void applyGameCommand(String action, Coordinate coordinate) {
                switch (action) {
                    case FLAG_ACTION:
                        boardManager.applyFlag(coordinate);
                        break;
                    case CLICK_ACTION:
                        boardManager.applyClick(coordinate);
                        break;
                }
        }

        private void applyMenuCommand(String action) {
            switch (action) {
                case QUIT_ACTION:
                    game.end(QUIT);
                    break;
                case INFO_ACTION:
                    handler.gameRules();
                    break;
                case EASTER_EGG_ACTION:
                    easterEgg();
                    break;
            }
        }

        private void easterEgg() {
            if (!boardManager.isFirstClickMade())
                applyGameCommand(CLICK_ACTION, new Coordinate(1, 1));
            endGame(WON);
        }
    }

}