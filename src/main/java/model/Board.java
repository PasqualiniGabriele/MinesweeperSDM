package model;

import java.util.Set;

import static model.BoardUtils.*;

public class Board {
    private final Cell[][] cells;

    public Board(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new FreeCell(0);
            }
        }
    }

    public void fillWithBombs(int numOfBombs, Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = generateSafeZone(safeZoneCenter);
        Set<Coordinate> randomGeneratedBombs = generateRandomCoordinates(numOfBombs, safeZone, this);
        for (Coordinate bombCoordinate : randomGeneratedBombs) {
            cells[bombCoordinate.x()][bombCoordinate.y()] = new BombedCell();
            updateProximity(bombCoordinate, this);
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