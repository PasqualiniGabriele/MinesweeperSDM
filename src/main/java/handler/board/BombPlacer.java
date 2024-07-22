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
        Set<Coordinate> randomGeneratedBombs = generateRandomCoordinates(safeZone);
        for (Coordinate bombCoordinate : randomGeneratedBombs) {
            board.setCell(new BombedCell(), bombCoordinate);
            updateProximity(bombCoordinate);
        }
    }

    public static Set<Coordinate> generateSafeZone(Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = new HashSet<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                safeZone.add(new Coordinate(safeZoneCenter.x() + dx, safeZoneCenter.y() + dy));
            }
        }
        return safeZone;
    }

    public Set<Coordinate> generateRandomCoordinates(Set<Coordinate> safeZone) {
        Set<Coordinate> randomCoordinates = new HashSet<>();
        Random random = new Random();
        while (randomCoordinates.size() < configuration.getNumOfBombs()) {
            int x = random.nextInt(board.getWidth());
            int y = random.nextInt(board.getHeight());
            Coordinate coordinate = new Coordinate(x, y);
            if (!safeZone.contains(coordinate)) {
                randomCoordinates.add(coordinate);
            }
        }
        return randomCoordinates;
    }

    public void updateProximity(Coordinate bombCoordinate) {
        int bombX = bombCoordinate.x();
        int bombY = bombCoordinate.y();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinate nextCoordinate = new Coordinate(bombX + dx, bombY + dy);
                if (board.isValidCoordinate(nextCoordinate)) {
                    if (board.getCell(nextCoordinate) instanceof FreeCell freeCell) {
                        freeCell.setProximity(freeCell.getProximity() + 1);
                    }
                }
            }
        }
    }
}
