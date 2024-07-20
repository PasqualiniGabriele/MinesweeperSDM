package model;

import static cli.ConsoleColor.*;

public interface CellState {
    void reveal(Cell cell);

    void toggleFlag(Cell cell);

    String toString(Cell cell);

    boolean isClosedState();

    boolean isOpenState();
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

    @Override
    public boolean isClosedState() {
        return false;
    }

    @Override
    public boolean isOpenState() {
        return true;
    }
}


class ClosedState implements CellState {
    @Override
    public void reveal(Cell cell) {
        cell.setState(new OpenState());
    }

    @Override
    public void toggleFlag(Cell cell) {
        if (Cell.getEventManager() != null)
            Cell.getEventManager().onFlag();
        cell.setState(new FlaggedState());
    }

    @Override
    public String toString(Cell cell) {
        return GREY + "■" + RESET;
    }

    @Override
    public boolean isClosedState() {
        return true;
    }

    @Override
    public boolean isOpenState() {
        return false;
    }
}

class FlaggedState implements CellState {
    public final String FLAG = "⚑";

    @Override
    public void reveal(Cell cell) {

    }

    @Override
    public void toggleFlag(Cell cell) {
        if (Cell.getEventManager() != null)
            Cell.getEventManager().onUnflag();
        cell.setState(new ClosedState());
    }

    @Override
    public String toString(Cell cell) {
        return RED + FLAG + RESET;
    }

    @Override
    public boolean isClosedState() {
        return false;
    }

    @Override
    public boolean isOpenState() {
        return false;
    }
}