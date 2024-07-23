package model.cell;

import static cli.ConsoleColor.*;

/**
 * The {@code CellState} interface defines the contract for different states of a cell in a game.
 * Each state defines how the cell should behave when it is revealed, flagged, or displayed.
 */
public interface CellState {

    /**
     * Reveals the cell. The specific behavior of revealing a cell depends on the current state of the cell.
     *
     * @param cell the cell to be revealed
     */
    void reveal(Cell cell);

    /**
     * Toggles the flag on the cell. The specific behavior of toggling a flag depends on the current state of the cell.
     *
     * @param cell the cell on which the flag is toggled
     */
    void toggleFlag(Cell cell);

    /**
     * Returns a string representation of the cell's display icon based on its current state.
     *
     * @param cell the cell for which to obtain the display icon
     * @return the display icon for the cell
     */
    String toString(Cell cell);

    /**
     * Checks if the cell is in the closed state.
     *
     * @return {@code true} if the cell is in the closed state, {@code false} otherwise
     */
    boolean isClosedState();

    /**
     * Checks if the cell is in the open state.
     *
     * @return {@code true} if the cell is in the open state, {@code false} otherwise
     */
    boolean isOpenState();
}

/**
 * The {@code OpenState} class represents a cell that is in the open state.
 * In this state, the cell cannot be flagged or revealed again.
 */
class OpenState implements CellState {

    @Override
    public void reveal(Cell cell) {
    }

    @Override
    public void toggleFlag(Cell cell) {
    }

    @Override
    public String toString(Cell cell) {
        return cell.getIcon();
    }

    @Override
    public boolean isClosedState() {
        return false;
    }

    @Override
    public boolean isOpenState() {
        return true;
    }
}

/**
 * The {@code ClosedState} class represents a cell that is in the closed state.
 * In this state, the cell can be revealed or flagged.
 */
class ClosedState implements CellState {

    @Override
    public void reveal(Cell cell) {
        cell.setState(new OpenState());
    }

    @Override
    public void toggleFlag(Cell cell) {
        if (Cell.getEventManager() != null) {
            Cell.getEventManager().onFlag();
        }
        cell.setState(new FlaggedState());
    }

    @Override
    public String toString(Cell cell) {
        return GREY + "■" + RESET;
    }

    @Override
    public boolean isClosedState() {
        return true;
    }

    @Override
    public boolean isOpenState() {
        return false;
    }
}

/**
 * The {@code FlaggedState} class represents a cell that is flagged.
 * In this state, the cell cannot be revealed and can only be unflagged.
 */
class FlaggedState implements CellState {

    public final String FLAG = "⚑";

    @Override
    public void reveal(Cell cell) {
    }

    @Override
    public void toggleFlag(Cell cell) {
        if (Cell.getEventManager() != null) {
            Cell.getEventManager().onUnflag();
        }
        cell.setState(new ClosedState());
    }

    @Override
    public String toString(Cell cell) {
        return RED + FLAG + RESET;
    }

    @Override
    public boolean isClosedState() {
        return false;
    }

    @Override
    public boolean isOpenState() {
        return false;
    }
}