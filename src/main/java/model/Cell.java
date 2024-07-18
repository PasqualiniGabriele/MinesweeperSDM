package model;

public abstract class Cell {
    private CellState state;
    private String icon;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return state.toString(this);
    }

    public boolean isClosedCell() {
        return state.isClosedState();
    }

    public boolean isOpenCell(){
        return state.isOpenState();
    };
}
