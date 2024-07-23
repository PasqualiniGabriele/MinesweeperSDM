package handler.board;

import handler.game.GameEventListener;
import handler.game.GameEventManager;
import model.board.Board;
import model.board.Configuration;
import model.board.Coordinate;
import model.cell.Cell;
import model.cell.FreeCell;

public class BoardManager implements GameEventListener {

    private final Board board;
    private int freeCellsLeft;
    private int flagsLeft;
    private boolean firstClickMade = false;
    private final BombPlacer bombPlacer;

    public BoardManager(Configuration configuration) {
        board = new Board(configuration);
        freeCellsLeft = configuration.getWidth() * configuration.getHeight() - configuration.getNumOfBombs();
        flagsLeft = configuration.getNumOfBombs();
        bombPlacer = new BombPlacer(configuration, board);
        GameEventManager.getInstance().subscribe(this);
        Cell.setEventManager(GameEventManager.getInstance());
    }

    public Board getBoard() {
        return board;
    }

    public void applyFlag(Coordinate coordinate) {
        Cell cell = board.getCell(coordinate);
        cell.toggleFlag();
    }

    public void applyClick(Coordinate coordinate) {
        if (!firstClickMade) {
            bombPlacer.placeBombsAvoiding(coordinate);
            firstClickMade = true;
        }
        revealCell(coordinate);
    }

    public void revealCell(Coordinate coordinate) {
        if (!isValidCoordinateForReveal(coordinate)) {
            return;
        }
        Cell cell = board.getCell(coordinate);
        cell.reveal();

        if (cell instanceof FreeCell freeCell && freeCell.isZeroProximity()) {
            revealSurroundingCells(coordinate);
        }
    }

    private boolean isValidCoordinateForReveal(Coordinate coordinate) {
        return board.isValidCoordinate(coordinate) && board.getCell(coordinate).isClosedCell();
    }

    private void revealSurroundingCells(Coordinate coordinate) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate nextCoordinate = new Coordinate(coordinate.x() + dx, coordinate.y() + dy);
                revealCell(nextCoordinate);
            }
        }
    }

    public void openAllCells() {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.getCell(new Coordinate(i, j)).open();
            }
        }
    }

    public int getFreeCellsLeft() {
        return freeCellsLeft;
    }

    public void decrementFreeCellsLeft() {
        freeCellsLeft--;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public Configuration getConfiguration() {
        return board.getConfiguration();
    }

    public boolean isFirstClickMade() {
        return firstClickMade;
    }

    public void setFirstClickMade(boolean firstClickMade) {
        this.firstClickMade = firstClickMade;
    }

    @Override
    public void onUnflag() {
        flagsLeft++;
    }

    @Override
    public void onFlag() {
        flagsLeft--;
    }
}
