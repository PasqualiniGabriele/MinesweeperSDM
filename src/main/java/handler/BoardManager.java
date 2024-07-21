package handler;

import model.*;

public class BoardManager {

    private final Board board;
    private int freeCellsLeft;
    private int flagsLeft;
    private final BombPlacer bombPlacer;

    public BoardManager(Configuration configuration) {
        board = new Board(configuration);
        freeCellsLeft = configuration.getWidth() * configuration.getHeight() - configuration.getNumOfBombs();
        flagsLeft = configuration.getNumOfBombs();
        bombPlacer = new BombPlacer(configuration, board);
        Cell.setEventManager(GameEventManager.getInstance());
    }

        void applyFirstClick(GameCommand firstCommand) {
            bombPlacer.placeBombsAvoiding(firstCommand.getCoordinate());
            applyClick(firstCommand.getCoordinate());
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
                freeCellsLeft--;
            }
        }

    public void openAllCells() {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.getCell(new Coordinate(i, j)).open();
            }
        }
    }

    public int getFreeCellsLeft() {
        return freeCellsLeft;
    }

    public void incrementFlagCounter() {
        flagsLeft++;
    }

    public void decrementFlagCounter() {
        flagsLeft--;
        System.out.println(flagsLeft);
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public Configuration getConfiguration() {
        return board.getConfiguration();
    }
}
