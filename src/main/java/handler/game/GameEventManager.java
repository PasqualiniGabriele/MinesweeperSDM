package handler.game;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameEventManager} class manages game events and notifies subscribed listeners of various game events.
 * This class follows the singleton pattern to ensure only one instance of the event manager exists.
 */
public class GameEventManager {
    private static GameEventManager instance;
    private final List<GameEventListener> listeners = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private GameEventManager() {
    }

    /**
     * Returns the singleton instance of the GameEventManager.
     *
     * @return the singleton instance of the GameEventManager
     */
    public static GameEventManager getInstance() {
        if (instance == null) {
            instance = new GameEventManager();
        }
        return instance;
    }

    /**
     * Subscribes a listener to game events.
     *
     * @param listener the listener to subscribe
     */
    public void subscribe(GameEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Unsubscribes all listeners from game events.
     */
    public void unsubscribeAll() {
        listeners.clear();
    }

    /**
     * Notifies all subscribed listeners of a bomb reveal event.
     */
    public void onBombReveal() {
        for (GameEventListener listener : listeners) {
            listener.onBombReveal();
        }
    }

    /**
     * Notifies all subscribed listeners of an unflag event.
     */
    public void onUnflag() {
        for (GameEventListener listener : listeners) {
            listener.onUnflag();
        }
    }

    /**
     * Notifies all subscribed listeners of a flag event.
     */
    public void onFlag() {
        for (GameEventListener listener : listeners) {
            listener.onFlag();
        }
    }

    /**
     * Notifies all subscribed listeners of a free cell reveal event.
     */
    public void onFreeCellReveal() {
        for (GameEventListener listener : listeners) {
            listener.onFreeCellReveal();
        }
    }
}
