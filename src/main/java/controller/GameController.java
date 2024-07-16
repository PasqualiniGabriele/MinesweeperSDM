package controller;

import cli.Command;
import model.*;

public class GameController {

    private Game game;
    private BoardManager boardManager;

    private boolean firstClick = true;

    public GameController() {
    }

    public void createGame(String inputDifficulty) {
        Difficulty difficulty = Difficulty.valueOf(inputDifficulty);
        game = new Game(difficulty);
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
                    boardManager.fillWithBombs(command.coordinate());
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