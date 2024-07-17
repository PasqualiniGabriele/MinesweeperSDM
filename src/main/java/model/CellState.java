package model;

public interface CellState {
    void reveal(Cell cell);
    void toggleFlag(Cell cell);
    public String toString(Cell cell);
}


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
}


class ClosedState implements CellState {
    @Override
    public void reveal(Cell cell) {
        cell.setState(new OpenState());
    }

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new FlaggedState());
    }

    @Override
    public String toString(Cell cell) {
        return " ";
    }
}

class FlaggedState implements CellState {
    public final String FLAG = "⚑";

    @Override
    public void reveal(Cell cell) {

    }

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new ClosedState());
    }

    @Override
    public String toString(Cell cell) {
        return FLAG;
    }


}