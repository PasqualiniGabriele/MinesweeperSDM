package model.cell;

import handler.game.GameEventManager;

/**
 * The {@code Cell} class is an abstract class that represents a cell in the context of a game, such as a minesweeper field.
 * A cell has a state that can be open, closed or flagged, and it may or may not contain a mine.
 */

public abstract class Cell {
    private CellState state;
    private String icon;
    static GameEventManager eventManager;

    /**
     * Sets the game event manager.
     *
     * @param eventManager the game event manager to set
     */
    public static void setEventManager(GameEventManager eventManager) {
        Cell.eventManager = eventManager;
    }

    /**
     * Returns the game event manager.
     *
     * @return the game event manager
     */
    public static GameEventManager getEventManager() {
        return eventManager;
    }

    /**
     * Constructs a {@code Cell} instance and initializes its state to {@code ClosedState}.
     */
    public Cell() {
        this.state = new ClosedState();
    }

    /**
     * Sets the state of the cell.
     *
     * @param state the new state of the cell
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Returns the current state of the cell.
     *
     * @return the current state of the cell
     */
    public CellState getState() {
        return state;
    }

    /**
     * Reveals the cell by invoking the reveal method of the current state.
     */
    public void reveal() {
        state.reveal(this);
    }

    /**
     * Determines if the cell contains a mine. This method must be implemented
     * by concrete classes that extend {@code Cell}.
     *
     * @return {@code true} if the cell contains a mine, {@code false} otherwise
     */
    public abstract boolean hasMine();

    /**
     * Toggles the flag on the cell by invoking the toggleFlag method of the current state.
     */
    public void toggleFlag() {
        state.toggleFlag(this);
    }

    /**
     * Returns the icon associated with the cell.
     *
     * @return the cell's icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the icon for the cell.
     *
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Opens the cell by changing its state to {@code OpenState}.
     */
    public void open() {
        state = new OpenState();
    }

    /**
     * Returns a string representation of the cell based on its state.
     *
     * @return the string representation of the cell
     */
    @Override
    public String toString() {
        return state.toString(this);
    }

    /**
     * Checks if the cell is closed.
     *
     * @return {@code true} if the cell is closed, {@code false} otherwise
     */
    public boolean isClosedCell() {
        return state.isClosedState();
    }

    /**
     * Checks if the cell is open.
     *
     * @return {@code true} if the cell is open, {@code false} otherwise
     */
    public boolean isOpenCell() {
        return state.isOpenState();
    }
}