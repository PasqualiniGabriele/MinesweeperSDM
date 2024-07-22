package handler;

import model.Board;
import model.Configuration;
import model.GameStatus;

public abstract class UIHandler {

    public UIHandler() {
    }

    protected abstract void gameRules();

    protected abstract void exit(GameStatus gameStatus);

    public abstract Configuration askForConfiguration();

    public abstract void welcome();

    public abstract Command hasNextCommand();

    public abstract void show(String[] gameStats, Board board);

    public abstract boolean isNewGameRequested();

    public abstract void printWrongCoordinateError();
}
