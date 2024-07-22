package cli;

import handler.Command;
import handler.GameCommand;
import model.Coordinate;

import java.util.Set;

import static handler.Command.*;

public class CommandParser {

    private static final Set<String> VALID_MENU_ACTIONS = Set.of(INFO_ACTION, QUIT_ACTION, EASTER_EGG_ACTION);
    private static final Set<String> VALID_GAME_ACTIONS = Set.of(FLAG_ACTION, CLICK_ACTION);

    public static Command parseCommand(String userInput) throws IllegalArgumentException {
        String[] commandArray = userInput.split(" ");
        String action = commandArray[0].toUpperCase();
        if ((commandArray.length == 1) && (isValidMenuAction(action))) {
            return new Command(action);
        } else if ((commandArray.length == 3) && (isValidGameCommand(action, commandArray))) {
            Coordinate coordinate = parseCoordinate(commandArray);
            return new GameCommand(action, coordinate);
        } else throw new IllegalArgumentException("Not a valid command\nValid format: C x y or F x y");
    }

    private static boolean isValidMenuAction(String action) {
        return VALID_MENU_ACTIONS.contains(action);
    }

    private static boolean isValidGameCommand(String action, String[] commandArray) {
        return (isValidGameAction(action) && isValidNumber(commandArray));
    }

    private static boolean isValidGameAction(String action) {
        return VALID_GAME_ACTIONS.contains(action);
    }

    public static Coordinate parseCoordinate(String[] commandArray) {
        int x = Integer.parseInt(commandArray[1]) - 1;
        int y = Integer.parseInt(commandArray[2]) - 1;
        return new Coordinate(x, y);
    }

    private static boolean isValidNumber(String[] commandArray) {
        try {
            int x = Integer.parseInt(commandArray[1]);
            int y = Integer.parseInt(commandArray[2]);
            return x > 0 && y > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}