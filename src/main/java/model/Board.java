package model;

public class Board {
    private Cell[][] cells;
    private Configuration configuration;

    public Board(Configuration configuration) {
        this.configuration = configuration;
        int width = configuration.getWidth();
        int height = configuration.getHeight();
        initializeCells(width, height);
    }

    public Board(int width, int height) {
        initializeCells(width, height);
    }

    private void initializeCells(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new FreeCell(0);
            }
        }
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Cell getCell(Coordinate coordinate) {
        return cells[coordinate.x()][coordinate.y()];
    }

    public boolean isValidCoordinate(Coordinate c) {
        return (c.x() >= 0 && c.y() >= 0 && c.x() < getWidth() && c.y() < getHeight());
    }

    public void setCell(Cell cell, Coordinate coordinate) {
        cells[coordinate.x()][coordinate.y()] = cell;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}