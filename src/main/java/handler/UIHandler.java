package handler;

import cli.Command;
import model.Board;
import model.Difficulty;

public abstract class UIHandler {

    public UIHandler() {
    }

    protected abstract void gameRules();

    protected abstract void exit();

    public abstract Difficulty askForDifficulty();

    public abstract void welcome();

    public abstract Command hasNextCommand();

    public void show(Board board) {
    }
}
