package model;

public class FreeCell extends Cell{
    public int proximity;

    public FreeCell(int proximity) {
        super();
        this.proximity = proximity;
    }
    @Override
    public boolean hasMine() {
        return false;
    }
    public int getProximity() {
        return proximity;
    }

    public void setProximity(int proximity) {
        this.proximity = proximity;
    }

    public String toString(){
        String s = "";
        if (getState() instanceof ClosedState){
            s = " ";
        } else if (getState() instanceof FlaggedState) {
            s = "🚩";
        } else if (getState() instanceof OpenState) {
            s = String.valueOf(proximity);
        }
        return s;
    }
}
