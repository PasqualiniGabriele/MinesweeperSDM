package model.cell;

import static cli.ConsoleColor.*;

/**
 * The {@code BombedCell} class represents a cell that contains a bomb.
 * This cell is a specific type of {@code Cell} that displays a bomb icon and has
 * specific behavior when revealed.
 */
public class BombedCell extends Cell {

    public static final String BOMB_ICON = RED + "●" + RESET;

    /**
     * Constructs a {@code BombedCell} instance.
     * Initializes the cell with the bomb icon.
     */
    public BombedCell() {
        super();
        setIcon(BOMB_ICON);
    }

    /**
     * Determines if this cell contains a mine (bomb).
     *
     * @return {@code true} as this cell always contains a bomb
     */
    @Override
    public boolean hasMine() {
        return true;
    }

    /**
     * Reveals the cell. If the cell is in the closed state, it triggers a bomb reveal event
     * through the event manager, if available, and then performs the standard reveal operation.
     * Overrides the {@code reveal} method from the {@code Cell} class to handle the bomb-specific behavior.
     */
    @Override
    public void reveal() {
        if (isClosedCell() && getEventManager() != null) {
            getEventManager().onBombReveal();
        }
        super.reveal();
    }
}
