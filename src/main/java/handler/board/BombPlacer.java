package handler.board;

import model.board.Board;
import model.board.Coordinate;
import model.cell.BombedCell;
import model.cell.FreeCell;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The {@code BombPlacer} class is responsible for placing bombs on a {@link Board} while avoiding a specified safe zone.
 * It handles the generation of bomb coordinates, ensuring that bombs do not fall within the defined safe zone. Additionally,
 * it updates the proximity counts of cells surrounding each bomb.
 */
public class BombPlacer {
    private final Board board;

    /**
     * Constructs a {@code BombPlacer} instance for the specified {@code Board}.
     *
     * @param board the {@code Board} on which bombs will be placed
     */
    public BombPlacer(Board board) {
        this.board = board;
    }

    /**
     * Places bombs on the board, ensuring that bombs are not placed within the specified safe zone.
     *
     * @param safeZoneCenter the center coordinate of the safe zone where bombs should not be placed
     */
    public void placeBombsAvoiding(Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = generateSafeZone(safeZoneCenter);
        Set<Coordinate> bombCoordinates = generateBombCoordinates(safeZone);
        placeBombs(bombCoordinates);
    }

    /**
     * Generates a set of coordinates that constitute the safe zone around the given center coordinate.
     *
     * @param safeZoneCenter the center coordinate of the safe zone
     * @return a set of coordinates that are within the safe zone
     */
    private Set<Coordinate> generateSafeZone(Coordinate safeZoneCenter) {
        Set<Coordinate> safeZone = new HashSet<>();
        safeZone.add(safeZoneCenter);
        for (Coordinate neighborCoordinate : getNeighbors(safeZoneCenter)) {
            if (board.isValidCoordinate(neighborCoordinate)) {
                safeZone.add(neighborCoordinate);
            }
        }
        return safeZone;
    }

    /**
     * Generates a set of bomb coordinates, avoiding the specified safe zone.
     *
     * @param safeZone a set of coordinates that should be avoided for bomb placement
     * @return a set of coordinates where bombs will be placed
     */
    private Set<Coordinate> generateBombCoordinates(Set<Coordinate> safeZone) {
        Set<Coordinate> bombCoordinates = new HashSet<>();
        while (bombCoordinates.size() < board.getNumOfBombs()) {
            Coordinate coordinate = generateRandomCoordinate();
            if (!safeZone.contains(coordinate)) {
                bombCoordinates.add(coordinate);
            }
        }
        return bombCoordinates;
    }

    /**
     * Generates a random coordinate within the boundaries of the board.
     *
     * @return a randomly generated {@code Coordinate} within the board's width and height
     */
    private Coordinate generateRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        return new Coordinate(x, y);
    }

    /**
     * Places bombs on the board at the specified coordinates and updates the proximity counts of surrounding cells.
     *
     * @param bombCoordinates a set of coordinates where bombs will be placed
     */
    public void placeBombs(Set<Coordinate> bombCoordinates) {
        for (Coordinate bombCoordinate : bombCoordinates) {
            board.setCell(new BombedCell(), bombCoordinate);
            updateProximity(bombCoordinate);
        }
    }

    /**
     * Updates the proximity count of each {@link FreeCell} surrounding a bomb.
     *
     * @param bombCoordinate the coordinate of the bomb whose surrounding cells' proximity counts are to be updated
     */
    private void updateProximity(Coordinate bombCoordinate) {
        for (Coordinate neighborCoordinate : getNeighbors(bombCoordinate)) {
            if (board.isValidCoordinate(neighborCoordinate) && board.getCell(neighborCoordinate) instanceof FreeCell freeCell) {
                freeCell.setProximity(freeCell.getProximity() + 1);
            }
        }
    }

    /**
     * Retrieves the set of neighboring coordinates surrounding a given coordinate.
     *
     * @param center the center coordinate from which neighbors are to be retrieved
     * @return a set of coordinates representing the neighbors around the given center coordinate
     */
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
