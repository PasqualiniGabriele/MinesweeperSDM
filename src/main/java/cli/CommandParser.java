package cli;

import model.Coordinate;

public class CommandParser {
    public static Command parseCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String action = inputArray[0];
        int x = Integer.parseInt(inputArray[1]);
        int y = Integer.parseInt(inputArray[2]);

        return new Command(action, new Coordinate(x, y));
    }
}
