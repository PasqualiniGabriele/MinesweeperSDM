package cli;

import handler.Command;
import handler.GameCommand;
import model.Coordinate;

import java.util.Set;

public class CommandParser {

    private static final Set<String> VALID_GAME_ACTIONS = Set.of("F", "C");
    private static final Set<String> VALID_MENU_ACTIONS = Set.of("I", "Q", "R");

    public static Command parseCommand(String userInput) throws IllegalArgumentException {
        String[] commandArray = userInput.split(" ");
        if (!isValidCommand(commandArray)) {
            throw new IllegalArgumentException("Invalid command format");
        }

        String action = commandArray[0].toUpperCase();
        int x = Integer.parseInt(commandArray[1])-1;
        int y = Integer.parseInt(commandArray[2])-1;
        return new GameCommand(action, new Coordinate(x, y));
    }

    private static boolean isValidCommand(String[] commandArray) {
        String action = commandArray[0].toUpperCase();
        if (!isValidAction(action)) return false;
        return (isValidNumber(commandArray) && isValidLength(commandArray));
    }

    private static boolean isValidNumber(String[] commandArray) {
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

    private static boolean isValidLength(String[] commandArray) {
        return (commandArray.length == 3);
    }

    private static boolean isValidAction(String action) {
        return VALID_GAME_ACTIONS.contains(action);
    }
}