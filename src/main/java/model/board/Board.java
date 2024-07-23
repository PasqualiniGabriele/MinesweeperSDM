package model.board;

import model.cell.Cell;
import model.cell.FreeCell;

/**
 * The {@code Board} class is used to manage the state of a game board, including
 * initializing the cells, retrieving and updating cell values, and validating coordinates.
 */
public class Board {
    private Cell[][] cells;
    private final Configuration configuration;

    /**
     * Constructs a {@code Board} using the provided configuration, initializing all cells
     * as {@link FreeCell} instances with a value of 0.
     *
     * @param configuration The board's configuration, specifying its width and height.
     */
    public Board(Configuration configuration) {
        this.configuration = configuration;
        int width = configuration.getWidth();
        int height = configuration.getHeight();
        initializeCells(width, height);
    }

    /**
     * Initializes the board's cells as {@link FreeCell} instances with a value of 0.
     *
     * @param width The width of the board.
     * @param height The height of the board.
     */
    private void initializeCells(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new FreeCell(0);
            }
        }
    }

    /**
     * Returns the width of the board.
     *
     * @return The width of the board.
     */
    public int getWidth() {
        return configuration.getWidth();
    }

    /**
     * Returns the height of the board.
     *
     * @return The height of the board.
     */
    public int getHeight() {
        return configuration.getHeight();
    }

    public int getNumOfBombs() {
        return configuration.getNumOfBombs();
    }

    /**
     * Retrieves the cell at the specified coordinates.
     *
     * @param coordinate The coordinates of the cell to retrieve.
     * @return The {@code Cell} at the specified coordinates.
     */
    public Cell getCell(Coordinate coordinate) {
        return cells[coordinate.x()][coordinate.y()];
    }

    /**
     * Checks if the specified coordinates are within the bounds of the board.
     *
     * @param c The coordinates to check.
     * @return {@code true} if the coordinates are valid; {@code false} otherwise.
     */
    public boolean isValidCoordinate(Coordinate c) {
        return (c.x() >= 0 && c.y() >= 0 && c.x() < getWidth() && c.y() < getHeight());
    }

    /**
     * Sets the cell at the specified coordinates to the given cell.
     *
     * @param cell The cell to set at the specified coordinates.
     * @param coordinate The coordinates where the cell should be placed.
     */
    public void setCell(Cell cell, Coordinate coordinate) {
        cells[coordinate.x()][coordinate.y()] = cell;
    }

    /**
     * Returns the configuration of the board.
     *
     * @return The {@code Configuration} of the board.
     */
    public Configuration getConfiguration() {
        return configuration;
    }
}