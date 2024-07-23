package model.game;

import java.time.Duration;
import java.time.LocalDateTime;

public class Game {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameStatus status;

    public Game() {
        startTime = LocalDateTime.now();
        status = GameStatus.ONGOING;
    }

    public long calculateGameTime() {
        LocalDateTime now = LocalDateTime.now();

        if (startTime == null) {
            startTime = now;
        }

        LocalDateTime end = (endTime != null) ? endTime : now;

        return Duration.between(startTime, end).getSeconds();
    }


    public void end(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    public GameStatus getStatus() {
        return status;
    }
}
