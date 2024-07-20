package handler;

import model.Coordinate;

public class Command {
    String action;
    Coordinate coordinate;
    public Command(String action, Coordinate coordinate){
        this.action = action;
        this.coordinate = coordinate;
    }
    public String getAction() {
        return action;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
