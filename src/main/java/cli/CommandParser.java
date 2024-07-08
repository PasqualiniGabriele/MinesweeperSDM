package cli;

import model.Coordinate;

import java.util.Set;

public class CommandParser {
    private static final Set<String> VALID_ACTIONS = Set.of("F", "C");

    public static Command parseCommand(String userInput) throws IllegalArgumentException{

        String[] inputArray = userInput.split(" ");
        String action = inputArray[0];
        int x = Integer.parseInt(inputArray[1]);
        int y = Integer.parseInt(inputArray[2]);

        if (!VALID_ACTIONS.contains(action)) {
            throw new IllegalArgumentException();
        }

        return new Command(action, new Coordinate(x, y));
    }
}
