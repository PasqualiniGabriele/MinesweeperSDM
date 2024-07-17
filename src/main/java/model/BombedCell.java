package model;

public class BombedCell extends Cell {
    public final String BOMB = "✷";

    public BombedCell() {
        super();
        super.setIcon(BOMB);
    }

    @Override
    public boolean hasMine(){
        return true;
    }
}
