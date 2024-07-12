package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game {
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private final Difficulty difficulty;
    private GameStatus status;

    public Game(Difficulty difficulty) {
        startTime = LocalDateTime.now();
        status = GameStatus.ONGOING;
        this.difficulty = difficulty;
    }

    public Duration calculateGameTime() {
        return Duration.between(startTime, endTime);
    }

    public void end(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameStatus getStatus() {
        return status;
    }
}
