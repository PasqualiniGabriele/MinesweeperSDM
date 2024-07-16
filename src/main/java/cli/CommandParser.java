package cli;

import model.Coordinate;

import java.util.Set;

public class CommandParser {

    private static final Set<String> VALID_ACTIONS = Set.of("F", "C");

    public static Command parseCommand(String userInput) throws IllegalArgumentException {
        String[] commandArray = userInput.split(" ");
        if (!isValidCommand(commandArray)) {
            throw new IllegalArgumentException("Invalid command format");
        }

        String action = commandArray[0];
        int x = Integer.parseInt(commandArray[1])-1;
        int y = Integer.parseInt(commandArray[2])-1;
        return new Command(action, new Coordinate(x, y));
    }

    private static boolean isValidCommand(String[] commandArray) {
        if (commandArray.length != 3) {
            return false;
        }

        String action = commandArray[0];
        if (!VALID_ACTIONS.contains(action)) {
            return false;
        }

        try {
            int x = Integer.parseInt(commandArray[1]);
            int y = Integer.parseInt(commandArray[2]);

            if (x <= 0 || y <= 0) {
                throw new IllegalArgumentException("Coordinates must be positive");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}