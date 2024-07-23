package cli;

import handler.input.Command;
import handler.input.GameCommand;
import model.board.Coordinate;

import java.util.Set;

import static handler.input.Command.*;

public class CommandParser {

    private static Coordinate maxCoordinate;

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
        } else throw new IllegalArgumentException();
    }

    private static boolean isValidMenuAction(String action) {
        return VALID_MENU_ACTIONS.contains(action);
    }

    private static boolean isValidGameCommand(String action, String[] commandArray) {
        return (isValidGameAction(action) && isValidCoordinate(commandArray));
    }

    private static boolean isValidGameAction(String action) {
        return VALID_GAME_ACTIONS.contains(action);
    }

    public static Coordinate parseCoordinate(String[] commandArray) {
        int x = Integer.parseInt(commandArray[1]) - 1;
        int y = Integer.parseInt(commandArray[2]) - 1;
        return new Coordinate(x, y);
    }

    private static boolean isValidCoordinate(String[] commandArray) {
        try {
            int x = Integer.parseInt(commandArray[1]);
            int y = Integer.parseInt(commandArray[2]);
            if (x <= 0 && y <= 0)
                return false;
            if (x >= maxCoordinate.x() && y >= maxCoordinate.y())
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void setMaxCoordinate(int maxX, int maxY) {
        maxCoordinate = new Coordinate(maxX, maxY);
    }
}