package cli;

import model.Coordinate;

public record Command(String action, Coordinate coordinate) {
}
