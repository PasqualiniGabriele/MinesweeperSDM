package cli;

import controller.GameController;

public abstract class Handler {
    protected GameController gameController;

    public Handler() {
    }

    public Handler(GameController gameController) {
        this.gameController = gameController;
    }

    public abstract void launch();

    protected abstract void newGame();

    protected abstract void gameRules();

    protected abstract void exit();
}
