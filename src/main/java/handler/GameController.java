package handler;

import cli.Command;
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
        Configuration configuration = handler.askForDifficulty();
        createGame(configuration);
        gameLoop();
        handler.exit();
    }

    public void createGame(Configuration configuration) {
        game = new Game();
        boardManager = new BoardManager(configuration);
        GameEventManager.getInstance().subscribe(this);
    }

    public void gameLoop(){
        handler.show(getGameStats(), boardManager.getBoard());
        Command firstCommand = handler.hasNextCommand();
        boardManager.applyFirstClick(firstCommand);
        applyCommand(firstCommand);
        while (game.getStatus() == GameStatus.ONGOING){
            handler.show(getGameStats(), boardManager.getBoard());
            Command command = handler.hasNextCommand();
            if (command == null) {
                break;
            }
            applyCommand(command);
        }
    }


    public void endGame(GameStatus endStatus) {
        game.end(endStatus);
    }


    public void applyCommand(Command command) {
        Coordinate coordinate = command.coordinate();
        switch (command.action()) {
            case "F":
                boardManager.applyFlag(coordinate);
                break;
            case "C":
                boardManager.applyClick(coordinate);
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
    public void onFlagReveal() {
        boardManager.decrementFlagCounter();
    }
}