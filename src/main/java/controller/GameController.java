package controller;

import cli.Command;
import model.*;

import java.sql.Time;
import java.time.LocalTime;

import static java.time.LocalTime.now;

public class GameController {

    private Game game;
    private Board board;

    private boolean firstClick;

    public GameController() {
    }

    public void createGame(String difficulty) {
        game = new Game();
        game.setDifficulty(Difficulty.valueOf(difficulty));
        Time now = Time.valueOf(now());
        game.start(now);
    }

    public void endGame(String endStatus) {
        switch (endStatus) {
            case "WON":
                game.setStatus(GameStatus.WON);
                break;
            case "LOST":
                game.setStatus(GameStatus.LOST);
                break;
            default:
                throw new IllegalArgumentException("Status not recognized");
        }
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