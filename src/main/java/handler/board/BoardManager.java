package handler.board;

import handler.game.GameEventListener;
import handler.game.GameEventManager;
import model.board.Board;
import model.board.Configuration;
import model.board.Coordinate;
import model.cell.Cell;
import model.cell.FreeCell;

/**
 * The {@code BoardManager} class manages the state and behavior of the game board.
 * It handles the placement of bombs, cell reveals, and flag operations.
 * Implements {@link GameEventListener} to respond to game events.
 */
public class BoardManager implements GameEventListener {

    private final Board board;
    private int freeCellsLeft;
    private int flagsLeft;
    private boolean firstClickMade = false;
    private final BombPlacer bombPlacer;

    /**
     * Constructs a {@code BoardManager} with the specified configuration.
     * Initializes the game board and subscribes to the game event manager.
     *
     * @param configuration the configuration of the board
     */
    public BoardManager(Configuration configuration) {
        board = new Board(configuration);
        freeCellsLeft = configuration.getWidth() * configuration.getHeight() - configuration.getNumOfBombs();
        flagsLeft = configuration.getNumOfBombs();
        bombPlacer = new BombPlacer(board);
        GameEventManager.getInstance().subscribe(this);
        Cell.setEventManager(GameEventManager.getInstance());
    }

    /**
     * Returns the game board managed by this {@code BoardManager}.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Toggles a flag on the cell at the specified coordinate.
     *
     * @param coordinate the coordinate of the cell to flag/unflag
     */
    public void applyFlag(Coordinate coordinate) {
        Cell cell = board.getCell(coordinate);
        cell.toggleFlag();
    }

    /**
     * Applies a click on the cell at the specified coordinate.
     * On the first click, bombs are placed avoiding the clicked cell.
     *
     * @param coordinate the coordinate of the clicked cell
     */
    public void applyClick(Coordinate coordinate) {
        if (!firstClickMade) {
            bombPlacer.placeBombsAvoiding(coordinate);
            firstClickMade = true;
        }
        revealCell(coordinate);
    }

    /**
     * Reveals the cell at the specified coordinate.
     * If the cell is a {@link FreeCell} with zero proximity, surrounding cells are revealed recursively.
     *
     * @param coordinate the coordinate of the cell to reveal
     */
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

    /**
     * Checks if the coordinate is valid for revealing a cell.
     *
     * @param coordinate the coordinate to check
     * @return {@code true} if the coordinate is valid and the cell is closed, {@code false} otherwise
     */
    private boolean isValidCoordinateForReveal(Coordinate coordinate) {
        return board.isValidCoordinate(coordinate) && board.getCell(coordinate).isClosedCell();
    }

    /**
     * Reveals all surrounding cells of the specified coordinate.
     *
     * @param coordinate the central coordinate
     */
    private void revealSurroundingCells(Coordinate coordinate) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate nextCoordinate = new Coordinate(coordinate.x() + dx, coordinate.y() + dy);
                revealCell(nextCoordinate);
            }
        }
    }

    /**
     * Opens all cells on the board.
     */
    public void openAllCells() {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.getCell(new Coordinate(i, j)).open();
            }
        }
    }

    /**
     * Returns the number of free cells left to be revealed.
     *
     * @return the number of free cells left
     */
    public int getFreeCellsLeft() {
        return freeCellsLeft;
    }

    /**
     * Decrements the count of free cells left to be revealed.
     */
    public void decrementFreeCellsLeft() {
        freeCellsLeft--;
    }

    /**
     * Returns the number of flags left.
     *
     * @return the number of flags left
     */
    public int getFlagsLeft() {
        return flagsLeft;
    }

    /**
     * Returns the configuration of the board.
     *
     * @return the board configuration
     */
    public Configuration getConfiguration() {
        return board.getConfiguration();
    }

    /**
     * Checks if the first click has been made.
     *
     * @return {@code true} if the first click has been made, {@code false} otherwise
     */
    public boolean isFirstClickMade() {
        return firstClickMade;
    }

    /**
     * Handles the event when a flag is removed from a cell.
     * Increments the count of flags left.
     */
    @Override
    public void onUnflag() {
        flagsLeft++;
    }

    /**
     * Handles the event when a flag is placed on a cell.
     * Decrements the count of flags left.
     */
    @Override
    public void onFlag() {
        flagsLeft--;
    }
}
