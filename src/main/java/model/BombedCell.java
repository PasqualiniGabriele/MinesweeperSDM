package model;

public class BombedCell extends Cell {
    public BombedCell() {
        super();
    }
    @Override
    public boolean hasMine(){
        return true;
    }
    public String toString(){
        String s = "";
        if (getState() instanceof ClosedState){
            s = " ";
        } else if (getState() instanceof FlaggedState) {
            s = "🚩";
        } else if (getState() instanceof OpenState) {
           s = "💣";
        }
        return s;
    }
}
