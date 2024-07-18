package handler;

import cli.Command;
import model.Board;
import model.Configuration;

public abstract class UIHandler {

    public UIHandler() {
    }

    protected abstract void gameRules();

    protected abstract void exit();

    public abstract Configuration askForDifficulty();

    public abstract void welcome();

    public abstract Command hasNextCommand();

    public void show(Board board) {
    }
}
