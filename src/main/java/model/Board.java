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

    private void updateProximity(Coordinate bombCoordinate){
    }


    public Cell getCell(Coordinate coordinate) {
        return cells[coordinate.x()][coordinate.y()];
    }

    public void setCell(Cell cell, Coordinate coordinate) {
        cells[coordinate.x()][coordinate.y()] = cell;
    }
}
