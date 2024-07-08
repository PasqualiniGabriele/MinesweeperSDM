package model;

import java.sql.Time;

public record Game (Time startTime, Time endTime, Status status, Difficulty difficulty) {
}

enum Status {
    WON,
    LOST,
    ONGOING
}

