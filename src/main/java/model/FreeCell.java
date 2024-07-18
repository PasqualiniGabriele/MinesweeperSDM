package model;

import static cli.DisplayFormatter.*;

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
        return switch(proximity){
            case 0 -> DARK_GREY + "-" + RESET;
            case 1 -> BLUE + proximity + RESET;
            case 2 -> GREEN + proximity + RESET;
            case 3 -> GOLD + proximity + RESET;
            case 4 -> PURPLE + proximity + RESET;
            case 5 -> CYAN + proximity + RESET;
            case 6 -> YELLOW + proximity + RESET;
            case 7 -> LIGHT_GREEN + proximity + RESET;
            case 8 -> GREY + proximity + RESET;
            default -> throw new IllegalStateException("Unexpected value: " + proximity);
        };
    }

    public boolean isZeroProximity() {
        return proximity == 0;
    }
}
