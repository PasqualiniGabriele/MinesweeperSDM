package handler.input;

import model.board.Board;
import model.board.Configuration;
import model.game.GameStatus;

public abstract class UIHandler {

    public UIHandler() {
    }

    public abstract void gameRules();

    public abstract void exit(GameStatus gameStatus);

    public abstract Configuration askConfigurationToUser();

    public abstract void renderWelcomeScreen();

    public abstract Command hasNextCommand();

    public abstract void renderGameScreen(String[] gameStats, Board board);

    public abstract boolean isNewGameRequested();
}
