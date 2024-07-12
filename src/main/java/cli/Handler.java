package cli;

import controller.GameController;

public abstract class Handler {
    private GameController gameController;

    public Handler() {
        gameController = new GameController();
    }

    public abstract void launch();

    protected abstract void startGame();

    protected abstract void gameRules();

    protected abstract void exit();

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
