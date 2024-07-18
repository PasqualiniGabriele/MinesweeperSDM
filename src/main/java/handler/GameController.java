package handler;

import cli.Command;
import model.*;

import java.util.logging.Handler;
public class GameController {

    private Game game;
    private BoardManager boardManager;
    private final UIHandler handler;
    private boolean firstClick = true;

    public GameController(UIHandler handler) {
        this.handler = handler;
    }

    public void launch() {
        handler.welcome();
        Difficulty difficulty = handler.askForDifficulty();
        createGame(difficulty);
        handler.exit();
    }

    public void createGame(Difficulty difficulty) {
        game = new Game();
        boardManager = new BoardManager(difficulty);
    }
    public void endGame(String endStatus) {
        game.end(GameStatus.valueOf(endStatus));
    }


    public void applyCommand(Command command) {
        Coordinate coordinate = command.coordinate();
        switch (command.action()) {
            case "F":
                boardManager.applyFlag(coordinate);
                break;
            case "C":
                if (firstClick) {
                    boardManager.placeBombsAvoiding(command.coordinate());
                    firstClick = false;
                }
                boardManager.applyClick(coordinate);
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    public Board getBoard() {
        return boardManager.getBoard();
    }

}