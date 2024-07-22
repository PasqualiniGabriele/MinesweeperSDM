package handler;

import model.*;

import static model.GameStatus.*;

public class GameController implements GameEventListener {

    private Game game;
    private BoardManager boardManager;
    private final UIHandler handler;


    public GameController(UIHandler handler) {
        this.handler = handler;
    }

    public void launch() {
        handler.welcome();
        do {
            Configuration configuration = handler.askForConfiguration();
            createGame(configuration);
            gameLoop();
        } while (handler.isNewGameRequested());
    }

    public void createGame(Configuration configuration) {
        game = new Game();
        boardManager = new BoardManager(configuration);
        GameEventManager.getInstance().subscribe(this);
    }

    public void gameLoop() {
        while (game.getStatus() == ONGOING) {
            handler.show(getGameStats(), boardManager.getBoard());
            Command command = handler.hasNextCommand();
            applyCommand(command);
        }
        GameEventManager.getInstance().unsubscribe(this);
        handler.exit(game.getStatus());
        handler.show(getGameStats(), boardManager.getBoard());
    }

    public void endGame(GameStatus endStatus) {
        boardManager.openAllCells();
        game.end(endStatus);
    }

    public void applyCommand(Command command) {
        if (command instanceof GameCommand gameCommand) {
            applyGameCommand(gameCommand.getAction(), gameCommand.getCoordinate());
        } else {
            applyMenuCommand(command.getAction());
        }
    }

    private void applyGameCommand(String action, Coordinate coordinate) {
        try {
            switch (action) {
                case "F":
                    boardManager.applyFlag(coordinate);
                    break;
                case "C":
                    boardManager.applyClick(coordinate);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            handler.printWrongCoordinateError();
        }
    }

    private void applyMenuCommand(String action) {
        switch (action) {
            case "Q":
                game.end(QUIT);
                break;
            case "I":
                handler.gameRules();
                break;
            case "SALAMUCCIO":
                easterEgg();
                break;
        }
    }

    private void easterEgg() {
        if (!boardManager.isFirstClickMade())
            applyGameCommand("C", new Coordinate(1, 1));
        endGame(WON);
    }

    public Game getGame() {
        return game;
    }

    public String[] getGameStats() {
        String[] stats = new String[3];
        stats[0] = String.valueOf(boardManager.getConfiguration());
        stats[1] = String.valueOf(boardManager.getFlagsLeft());
        stats[2] = String.valueOf(game.calculateGameTime());
        return stats;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public void onBombReveal() {
        endGame(LOST);
    }

    @Override
    public void onUnflag() {
        boardManager.incrementFlagCounter();
    }

    @Override
    public void onFlag() {
        boardManager.decrementFlagCounter();
    }

    @Override
    public void onFreeCellReveal() {
        boardManager.decrementFreeCellsLeft();
        if (boardManager.getFreeCellsLeft() == 0) {
            endGame(WON);
        }
    }
}