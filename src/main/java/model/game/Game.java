package model.game;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The {@code Game} class is used to track the duration of the game and its status throughout its lifecycle.
 */
public class Game {

    /**
     * This enum represents the various statuses of a game.
     */
    public enum Status {
        WON,
        LOST,
        QUIT,
        ONGOING
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;

    /**
     * Constructs a new {@code Game} instance.
     * Initializes the {@code startTime} to the current time and sets the {@code status}
     * to {@link Status#ONGOING}.
     */
    public Game() {
        startTime = LocalDateTime.now();
        status = Status.ONGOING;
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
     * @param status The final status of the game.
     */
    public void setEndStatus(Status status) {
        endTime = LocalDateTime.now();
        this.status = status;
    }

    /**
     * Returns the current status of the game.
     *
     * @return The current {@code GameStatus}.
     */
    public Status getStatus() {
        return status;
    }
}
