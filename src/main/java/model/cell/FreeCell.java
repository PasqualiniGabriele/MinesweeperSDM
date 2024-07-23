package model.cell;

import static cli.ConsoleColor.*;

/**
 * The {@code FreeCell} class represents a cell in a game that does not contain a mine
 * but indicates the number of adjacent mines. It displays a numeric value based on its
 * proximity to nearby mines, and has specific visual icons for each proximity value.
 */
public class FreeCell extends Cell {

    private int proximity;

    /**
     * Constructs a {@code FreeCell} instance with the specified proximity value.
     *
     * @param proximity the number of adjacent mines
     */
    public FreeCell(int proximity) {
        super();
        this.proximity = proximity;
    }

    /**
     * Indicates that this cell does not contain a mine.
     *
     * @return {@code false} as this cell does not contain a mine
     */
    @Override
    public boolean hasMine() {
        return false;
    }

    /**
     * Returns the number of adjacent mines for this cell.
     *
     * @return the proximity value of adjacent mines
     */
    public int getProximity() {
        return proximity;
    }

    /**
     * Sets the number of adjacent mines for this cell.
     *
     * @param proximity the new proximity value to set
     */
    public void setProximity(int proximity) {
        this.proximity = proximity;
    }

    /**
     * Reveals the cell. If the cell is in the closed state and an event manager is available,
     * it triggers a free cell reveal event. This method then performs the standard reveal operation
     * as defined in the {@code Cell} superclass.
     * Overrides the {@code reveal} method from the {@code Cell} class to handle free cell-specific behavior.
     */
    @Override
    public void reveal() {
        if (isClosedCell() && getEventManager() != null) {
            getEventManager().onFreeCellReveal();
        }
        super.reveal();
    }

    /**
     * Returns the display icon for the cell based on the proximity value.
     *
     * @return the icon representing the proximity value of the cell
     * @throws IllegalStateException if the proximity value is unexpected
     */
    @Override
    public String getIcon() {
        String proximityStr = String.valueOf(this.proximity);
        return switch (proximityStr) {
            case "0" -> DARK_GREY + "-" + RESET;
            case "1" -> BLUE + proximityStr + RESET;
            case "2" -> GREEN + proximityStr + RESET;
            case "3" -> GOLD + proximityStr + RESET;
            case "4" -> PURPLE + proximityStr + RESET;
            case "5" -> CYAN + proximityStr + RESET;
            case "6" -> YELLOW + proximityStr + RESET;
            case "7" -> LIGHT_GREEN + proximityStr + RESET;
            case "8" -> GREY + proximityStr + RESET;
            default -> throw new IllegalStateException("Unexpected value: " + proximityStr);
        };
    }

    /**
     * Checks if the proximity value is zero.
     *
     * @return {@code true} if the proximity is zero, {@code false} otherwise
     */
    public boolean isZeroProximity() {
        return proximity == 0;
    }
}