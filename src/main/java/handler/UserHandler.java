package handler;

public abstract class UserHandler {
    private GameController gameController;

    public UserHandler() {
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
