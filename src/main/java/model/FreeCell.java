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

    @Override
    public String getIcon() {
        return String.valueOf(proximity);
    }

    public boolean isZeroProximity() {
        return proximity == 0;
    }
}
