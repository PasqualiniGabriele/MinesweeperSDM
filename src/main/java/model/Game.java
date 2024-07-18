package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game {
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameStatus status;

    public Game() {
        startTime = LocalDateTime.now();
        status = GameStatus.ONGOING;
    }

    public Duration calculateGameTime() {
        return Duration.between(startTime, endTime);
    }

    public void end(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    public GameStatus getStatus() {
        return status;
    }
}
