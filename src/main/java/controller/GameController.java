package controller;

import cli.Command;
import model.*;

public class GameController {

    private Game game;
    private Board board;

    private boolean firstClick;

    public GameController() {
    }

    public void createGame(String inputDifficulty) {
        Difficulty difficulty = Difficulty.valueOf(inputDifficulty);
        game = new Game(difficulty);
        board = new Board(difficulty.getWidth(), difficulty.getHeight());
    }

    public void endGame(String endStatus) {
        game.end(GameStatus.valueOf(endStatus));
    }

    public void applyCommand(Command command) {
    }

    public Game getGame() {
        return game;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isFirstClick() {
        return firstClick;
    }

    public void setFirstClick(boolean firstClick) {
        this.firstClick = firstClick;
    }
}