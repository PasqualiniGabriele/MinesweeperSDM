package handler.board;

import model.board.Board;
import model.board.Configuration;
import model.board.Coordinate;
import model.cell.BombedCell;
import model.cell.FreeCell;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BombPlacer {
    private final Board board;
    private final Configuration configuration;

    public BombPlacer(Configuration configuration, Board board) {
        this.configuration = configuration;
        this.board = board;
    }

    public void placeBombsAvoiding(Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = generateSafeZone(safeZoneCenter);
        Set<Coordinate> bombCoordinates = generateBombCoordinates(safeZone);
        placeBombs(bombCoordinates);
    }

    private Set<Coordinate> generateSafeZone(Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = new HashSet<>();
        for (Coordinate neighborCoordinate : getNeighbors(safeZoneCenter)) {
            if (board.isValidCoordinate(neighborCoordinate))
                safeZone.add(neighborCoordinate);
        }
        return safeZone;
    }

    private Set<Coordinate> generateBombCoordinates(Set<Coordinate> safeZone) {
        Set<Coordinate> randomCoordinates = new HashSet<>();
        while (randomCoordinates.size() < configuration.getNumOfBombs()) {
            Coordinate coordinate = generateRandomCoordinate();
            if (!safeZone.contains(coordinate)) {
                randomCoordinates.add(coordinate);
            }
        }
        return randomCoordinates;
    }

    private Coordinate generateRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        return new Coordinate(x, y);
    }

    public void placeBombs(Set<Coordinate> bombCoordinates) {
        for (Coordinate bombCoordinate : bombCoordinates) {
            board.setCell(new BombedCell(), bombCoordinate);
            updateProximity(bombCoordinate);
        }
    }

    private void updateProximity(Coordinate bombCoordinate) {
        for (Coordinate neighborCoordinate : getNeighbors(bombCoordinate)) {
            if (board.isValidCoordinate(neighborCoordinate) && board.getCell(neighborCoordinate) instanceof FreeCell freeCell) {
                freeCell.setProximity(freeCell.getProximity() + 1);
            }
        }
    }

    private Set<Coordinate> getNeighbors(Coordinate center) {
        Set<Coordinate> neighbors = new HashSet<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    neighbors.add(new Coordinate(center.x() + dx, center.y() + dy));
                }
            }
        }
        return neighbors;
    }

}


