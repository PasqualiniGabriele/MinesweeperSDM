package cli;

import model.Coordinate;

public class CommandParser {
    public static Command parseCommand(String userInput) {
        return new Command("F", new Coordinate(1, 2));
    }
}
