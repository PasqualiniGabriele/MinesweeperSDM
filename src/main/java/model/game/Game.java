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
        if (startTime == null)
            startTime = LocalDateTime.now();
        if (endTime == null)
            return Duration.between(startTime, LocalDateTime.now()).getSeconds();
        return Duration.between(startTime, endTime).getSeconds();
    }

    public void end(GameStatus gameStatus) {
        endTime = LocalDateTime.now();
        status = gameStatus;
    }

    public GameStatus getStatus() {
        return status;
    }
}
