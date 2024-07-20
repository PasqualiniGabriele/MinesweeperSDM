package model;

import static cli.ConsoleColor.*;

public class BombedCell extends Cell {
    public final String BOMB = RED + "●" + RESET;

    public BombedCell() {
        super();
        super.setIcon(BOMB);
    }

    @Override
    public boolean hasMine() {
        return true;
    }

    @Override
    public void reveal() {
        if (isClosedCell() && eventManager != null) {
            eventManager.onBombReveal();
        }
        super.reveal();
    }
}
