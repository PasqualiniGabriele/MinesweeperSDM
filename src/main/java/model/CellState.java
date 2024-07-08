package model;

public interface CellState {
    void reveal(Cell cell);

    void toggleFlag(Cell cell);

}


class OpenState implements CellState {
    @Override
    public void reveal(Cell cell) {

    }

    @Override
    public void toggleFlag(Cell cell) {

    }
}


class ClosedState implements CellState {
    @Override
    public void reveal(Cell cell) {

    }

    @Override
    public void toggleFlag(Cell cell) {

    }
}

class FlaggedState implements CellState {
    @Override
    public void reveal(Cell cell) {

    }

    @Override
    public void toggleFlag(Cell cell) {

    }

}