package handler;

import java.util.ArrayList;
import java.util.List;

public class GameEventManager {
    private static GameEventManager instance;
    private final List<GameEventListener> listeners = new ArrayList<>();

    private GameEventManager() {
    }

    public static GameEventManager getInstance() {
        if (instance == null) {
            instance = new GameEventManager();
        }
        return instance;
    }

    public void subscribe(GameEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void onBombReveal() {
        for (GameEventListener listener : listeners) {
            listener.onBombReveal();
        }
    }

    public void onUnflag() {
        for (GameEventListener listener : listeners) {
            listener.onUnflag();
        }
    }

    public void onFlag() {
        for (GameEventListener listener : listeners) {
            listener.onFlag();
        }
    }

    public void onFreeCellReveal() {
        for (GameEventListener listener : listeners) {
            listener.onFreeCellReveal();
        }
    }
}
