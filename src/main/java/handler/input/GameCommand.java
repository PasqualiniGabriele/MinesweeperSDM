package handler.input;

import model.board.Coordinate;

public class GameCommand extends Command {
    Coordinate coordinate;

    public GameCommand(Action action, Coordinate coordinate) {
        super(action);
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}