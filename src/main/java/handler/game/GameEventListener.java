package handler.game;

/**
 * Interface for handling game events.
 */
public interface GameEventListener {

    /**
     * Called when a bomb is revealed in the game.
     */
    default void onBombReveal() {
    }

    /**
     * Called when a cell is unflagged in the game.
     */
    default void onUnflag() {
    }

    /**
     * Called when a cell is flagged in the game.
     */
    default void onFlag() {
    }

    /**
     * Called when a free cell is revealed in the game.
     */
    default void onFreeCellReveal() {
    }
}
