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
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if (!(x == 1 && y == 1)){
                    FreeCell freeCell = (FreeCell)(cells[x][y]);
                    (freeCell).setProximity(freeCell.getProximity()+1);
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
