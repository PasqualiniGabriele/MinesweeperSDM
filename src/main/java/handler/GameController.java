package handler;

import cli.Command;
import model.*;

public class GameController {

    private Game game;
    private BoardManager boardManager;
    private final UIHandler handler;

    public GameController(UIHandler handler) {
        this.handler = handler;
    }

    public void launch() {
        handler.welcome();
        Difficulty difficulty = handler.askForDifficulty();
        createGame(difficulty);
        gameLoop();
        handler.exit();
    }

    public void createGame(Difficulty difficulty) {
        game = new Game();
        boardManager = new BoardManager(difficulty);
    }

    public void gameLoop(){
        handler.show(boardManager.getBoard());
        Command firstCommand = handler.hasNextCommand();
        applyFirstClick(firstCommand);
        while (game.getStatus() == GameStatus.ONGOING){
            handler.show(boardManager.getBoard());
            Command command = handler.hasNextCommand();
            if (command == null) {
                break;
            }
            applyCommand(command);
        }
    }

    void applyFirstClick(Command firstCommand) {
        boardManager.placeBombsAvoiding(firstCommand.coordinate());
        applyCommand(firstCommand);
    }


    public void endGame(GameStatus endStatus) {
        game.end(endStatus);
    }


    public void applyCommand(Command command) {
        Coordinate coordinate = command.coordinate();
        switch (command.action()) {
            case "F":
                boardManager.applyFlag(coordinate);
                break;
            case "C":
                boardManager.applyClick(coordinate);
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

}