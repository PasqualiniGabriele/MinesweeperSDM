package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardUtils {

    private BoardUtils() {
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

    public static Set<Coordinate> generateRandomCoordinates(int n, Set<Coordinate> safeZone, Board board) {
        Set<Coordinate> randomCoordinates = new HashSet<>();
        Random random = new Random();
        while (randomCoordinates.size() < n) {
            int x = random.nextInt(board.getWidth());
            int y = random.nextInt(board.getHeight());
            Coordinate coordinate = new Coordinate(x, y);
            if (!safeZone.contains(coordinate)) {
                randomCoordinates.add(coordinate);
            }
        }
        return randomCoordinates;
    }

    public static void updateProximity(Coordinate bombCoordinate, Board board) {
        int bombX = bombCoordinate.x();
        int bombY = bombCoordinate.y();
        Cell cell;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (!(dx == 0 && dy == 0)) {
                    try {
                        cell = board.getCell(new Coordinate(bombX + dx,bombY + dy));
                        if (cell instanceof FreeCell freeCell) {
                            freeCell.setProximity(freeCell.getProximity() + 1);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }


}
