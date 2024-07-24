package handler.input;

import model.board.Coordinate;

/**
 * The {@code GameCommand} class represents a command in the game that includes an action and a coordinate.
 */
public class GameCommand extends Command {
    Coordinate coordinate;

    /**
     * Constructs a new {@code GameCommand} instance with the specified action and coordinate.
     *
     * @param action     The action to be performed.
     * @param coordinate The coordinate on the game board.
     */
    public GameCommand(Action action, Coordinate coordinate) {
        super(action);
        this.coordinate = coordinate;
    }

    /**
     * Returns the coordinate associated with this command.
     *
     * @return The {@code Coordinate} of this command.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the coordinate associated with this command.
     *
     * @param coordinate The {@code Coordinate} to set.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}