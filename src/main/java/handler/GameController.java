package handler;

import model.*;

public class GameController implements GameEventListener {

    private Game game;
    private BoardManager boardManager;
    private final UIHandler handler;


    public GameController(UIHandler handler) {
        this.handler = handler;
        BombedCell.setEventManager(GameEventManager.getInstance());
    }

    public void launch() {
        handler.welcome();
        Configuration configuration = handler.askForConfiguration();
        createGame(configuration);
        gameLoop();
        handler.exit(game.getStatus());
    }

    public void createGame(Configuration configuration) {
        game = new Game();
        boardManager = new BoardManager(configuration);
        GameEventManager.getInstance().subscribe(this);
    }

    public void gameLoop(){
        handler.show(getGameStats(), boardManager.getBoard());
        GameCommand firstCommand = (GameCommand) handler.hasNextCommand();
        boardManager.applyFirstClick(firstCommand);
        while (game.getStatus() == GameStatus.ONGOING){
            handler.show(getGameStats(), boardManager.getBoard());
            Command command = handler.hasNextCommand();
            if (command == null) {
                break;
            }
            applyCommand(command);
            if(boardManager.getFreeCellsLeft()==0){
                endGame(GameStatus.WON);
            }
        }
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
        switch (action) {
            case "F":
                boardManager.applyFlag(coordinate);
                break;
            case "C":
                boardManager.applyClick(coordinate);
                break;
        }
    }

    private void applyMenuCommand(String action) {
        switch (action) {
            case "Q":
                // implement quit logic
                break;
            case "R":
                // implement restart logic
                break;
            case "I":
                handler.gameRules();
                break;
        }
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
        endGame(GameStatus.LOST);
    }

    @Override
    public void onUnflag() {
        boardManager.incrementFlagCounter();
    }

    @Override
    public void onFlag() {
        boardManager.decrementFlagCounter();
    }
}