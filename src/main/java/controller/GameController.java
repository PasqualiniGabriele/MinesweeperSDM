package controller;

import cli.Command;
import model.*;

public class GameController {

    private Game game;
    private Board board;

    private boolean firstClick = true;

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
        Cell cell = board.getCell(command.coordinate());
        switch (command.action()) {
            case "F":
                cell.toggleFlag();
                break;
            case "C":
                if (firstClick) {
                    board.fillWithBombs(game.getDifficulty().getNumOfBombs(), command.coordinate());
                    firstClick = false;
                }
                BoardManager.revealAdjacentArea(command.coordinate(), board);
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

}