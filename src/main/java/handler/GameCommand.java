package handler;

import model.Coordinate;

public class GameCommand extends Command {
    Coordinate coordinate;
    public GameCommand(String action, Coordinate coordinate) {
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
