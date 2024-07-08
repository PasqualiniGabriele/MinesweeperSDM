package controller;

import cli.Command;
import model.Board;
import model.Game;

public class GameController {

    private Game game;
    private Board board;

    private boolean firstClick;

    public GameController() {
    }

    public void createGame(String difficulty) {
    }

    public void endGame() {
    }

    public void applyCommand(Command command) {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isFirstClick() {
        return firstClick;
    }

    public void setFirstClick(boolean firstClick) {
        this.firstClick = firstClick;
    }
}