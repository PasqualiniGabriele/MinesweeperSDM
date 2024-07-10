package model;

public class Board {
    private Cell[][] cells;

    public Board(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new FreeCell(0);
            }
        }
    }

    public void fillWithBombs(Coordinate safeZoneCenter, int numOfBombs) {

    }

    public void updateProximity(Coordinate bombCoordinate) {
        int bombX = bombCoordinate.x();
        int bombY = bombCoordinate.y();
        Cell cell;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (!(dx == 0 && dy == 0)) {
                    try {
                        cell = cells[bombX + dx][bombY + dy];
                        if (cell instanceof FreeCell freeCell) {
                            freeCell.setProximity(freeCell.getProximity() + 1);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }

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

    public void setCell(Cell cell, Coordinate coordinate) {
        cells[coordinate.x()][coordinate.y()] = cell;
    }
}
