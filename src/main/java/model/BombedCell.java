package model;

import handler.GameEventManager;

public class BombedCell extends Cell {
    private static GameEventManager eventManager;
    public final String BOMB = "✷";

    public BombedCell() {
        super();
        super.setIcon(BOMB);
    }

    public static void setEventManager(GameEventManager eventManager) {
        BombedCell.eventManager = eventManager;
    }

    @Override
    public boolean hasMine() {
        return true;
    }

    @Override
    public void reveal() {
        super.reveal();
        if (eventManager != null) {
            eventManager.onBombReveal();
        }
    }
}
