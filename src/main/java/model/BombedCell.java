package model;

import handler.GameEventManager;

public class BombedCell extends Cell {
    public final String BOMB = "✷";

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
        super.reveal();
        if (isClosedCell()) {
            eventManager.onBombReveal();
        }
    }
}
