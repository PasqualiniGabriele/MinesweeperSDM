package cli;

import controller.GameController;

public class CLIHandler extends Handler {
    CommandParser commandParser;
    DisplayFormatter displayFormatter;

    public CLIHandler() {
        super();
    }

    public CLIHandler(GameController gameController, CommandParser commandParser, DisplayFormatter displayFormatter) {
        super(gameController);
        this.commandParser = commandParser;
        this.displayFormatter = displayFormatter;
    }

    @Override
    public void launch() {
    }

    @Override
    protected void newGame() {
    }

    @Override
    protected void setDifficulty() {
    }

    @Override
    protected void gameRules() {

    }

    @Override
    protected void exit() {
    }

}
