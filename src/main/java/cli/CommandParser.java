package cli;

import handler.input.Command;
import handler.input.GameCommand;
import model.board.Coordinate;

import java.util.Map;

import static handler.input.Command.Action.*;

public class CommandParser {

    private static Coordinate maxCoordinate;

    private static final Map<String, Command.Action> VALID_MENU_ACTIONS = Map.of(
            "I", INFO_ACTION,
            "Q", QUIT_ACTION,
            "SALAMUCCIO", EASTER_EGG_ACTION
    );

    private static final Map<String, Command.Action> VALID_GAME_ACTIONS = Map.of(
            "F", FLAG_ACTION,
            "C", CLICK_ACTION
    );

    public static Command parseCommand(String userInput) throws IllegalArgumentException {
        String[] commandArray = userInput.split(" ");
        String action = commandArray[0].toUpperCase();

        if ((commandArray.length == 1) && (isValidMenuAction(action))) {
            return new Command(VALID_MENU_ACTIONS.get(action));
        }

        if ((commandArray.length == 3) && (isValidGameCommand(action, commandArray))) {
            Coordinate coordinate = parseCoordinate(commandArray);
            return new GameCommand(VALID_GAME_ACTIONS.get(action), coordinate);
        }

        throw new IllegalArgumentException();
    }

    private static boolean isValidMenuAction(String action) {
        return VALID_MENU_ACTIONS.containsKey(action);
    }

    private static boolean isValidGameCommand(String action, String[] commandArray) {
        return (isValidGameAction(action) && isValidCoordinate(commandArray));
    }

    private static boolean isValidGameAction(String action) {
        return VALID_GAME_ACTIONS.containsKey(action);
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
            return (isWithinBounds(x, y));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isWithinBounds(int x, int y) {
        return x > 0 && y > 0 && x <= maxCoordinate.x() && y <= maxCoordinate.y();
    }

    public static void setMaxCoordinate(int maxX, int maxY) {
        maxCoordinate = new Coordinate(maxX, maxY);
    }
}