package model;

public abstract class Cell {
    private CellState state;


    public Cell() {
        this.state = new ClosedState();
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }

    public void reveal() {
        state.reveal(this);
    }

    public abstract boolean hasMine();


    public void toggleFlag() {
        state.toggleFlag(this);
    }
}
