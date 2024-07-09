package model;

import java.sql.Time;

import static java.time.LocalTime.now;

public class Game {
    private Time startTime;
    private Time endTime;
    private Difficulty difficulty;
    private GameStatus status;

    public Game() {}

    public Game(Time startTime, Time endTime, Difficulty difficulty, GameStatus status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.difficulty = difficulty;
        this.status = status;
    }

    public Game(Time startTime, Difficulty difficulty, GameStatus status) {
        this(startTime, null, difficulty, status);
    }

    public void start() {
        startTime = Time.valueOf(now());
        status = GameStatus.ONGOING;
    }

    public void end(GameStatus gameStatus) {
        endTime = Time.valueOf(now());
        status = gameStatus;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
