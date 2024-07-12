package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Difficulty difficulty;
    private GameStatus status;

    public Game() {
    }

    public Game(LocalDateTime startTime, LocalDateTime endTime, Difficulty difficulty, GameStatus status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.difficulty = difficulty;
        this.status = status;
    }

    public Game(LocalDateTime startTime, Difficulty difficulty, GameStatus status) {
        this(startTime, null, difficulty, status);
    }

    public Duration calculateGameTime() {
        return Duration.between(startTime, endTime);
    }

    public void start() {
        startTime = LocalDateTime.now();
        status = GameStatus.ONGOING;
    }

    public void end(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
