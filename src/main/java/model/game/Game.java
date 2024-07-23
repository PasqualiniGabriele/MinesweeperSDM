package model.game;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The {@code Game} class is used to track the duration of the game and its status throughout its lifecycle.
 */
public class Game {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameStatus status;

    /**
     * Constructs a new {@code Game} instance.
     * Initializes the {@code startTime} to the current time and sets the {@code status}
     * to {@link GameStatus#ONGOING}.
     */
    public Game() {
        startTime = LocalDateTime.now();
        status = GameStatus.ONGOING;
    }

    /**
     * Calculates the elapsed time of the game in seconds.
     *
     * @return The elapsed time of the game in seconds.
     */
    public long calculateGameTime() {
        LocalDateTime now = LocalDateTime.now();

        if (startTime == null) {
            startTime = now;
        }

        LocalDateTime end = (endTime != null) ? endTime : now;

        return Duration.between(startTime, end).getSeconds();
    }

    /**
     * Ends the game and sets its status.
     *
     * @param gameStatus The final status of the game.
     */
    public void setEndStatus(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    /**
     * Returns the current status of the game.
     *
     * @return The current {@code GameStatus}.
     */
    public GameStatus getStatus() {
        return status;
    }
}
