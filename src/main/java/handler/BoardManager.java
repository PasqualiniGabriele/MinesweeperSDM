package handler;

import model.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardManager {

    private Board board;
    private int freeCellsLeft;
    private Configuration configuration;

    public BoardManager() {
    }

    public BoardManager(Configuration configuration) {
        board = new Board(configuration.getWidth(), configuration.getHeight());
        freeCellsLeft = configuration.getWidth() * configuration.getHeight() - configuration.getNumOfBombs();
        this.configuration = configuration;
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
                    Cell cell = board.getCell(nextCoordinate);
                    if (cell instanceof FreeCell freeCell) {
                        freeCell.setProximity(freeCell.getProximity() + 1);
                    }
                }
            }
        }
    }

    public void revealAdjacentArea(Coordinate coordinate) {
        if (!board.isValidCoordinate(coordinate) || !board.getCell(coordinate).isClosedCell()) {
            return;
        }
        FreeCell freeCell = (FreeCell) board.getCell(coordinate);
        freeCell.reveal();
        freeCellsLeft--;
        if (freeCell.isZeroProximity()) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    Coordinate nextCoordinate = new Coordinate(coordinate.x() + dx, coordinate.y() + dy);
                    revealAdjacentArea(nextCoordinate);
                }
            }
        }
    }

    public int getFreeCellsLeft() {
        return freeCellsLeft;
    }

    public Board getBoard() {
        return board;
    }

    public void applyFlag(Coordinate coordinate) {
        Cell cell = board.getCell(coordinate);
        cell.toggleFlag();
    }

    public void applyClick(Coordinate coordinate) {
        Cell cell = board.getCell(coordinate);
        if (cell instanceof FreeCell freeCell && (freeCell.getProximity() == 0)) {
            revealAdjacentArea(coordinate);
        } else {
            cell.reveal();
        }
    }
}
