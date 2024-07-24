package cli;

import handler.input.Command;
import handler.input.GameCommand;
import model.board.Coordinate;

import java.util.Map;

import static handler.input.Command.Action.*;

/**
 * The {@code CommandParser} class is responsible for parsing user input into valid {@link Command} objects.
 * It supports both menu actions and game commands by validating the input and converting it to the appropriate command.
 */
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

    /**
     * Parses the user input into a {@link Command} object.
     *
     * @param userInput The input string from the user.
     * @return A {@link Command} object representing the parsed command.
     * @throws IllegalArgumentException if the input string is not a valid command.
     */
    public static Command parseCommand(String userInput) throws IllegalArgumentException {
        String[] commandArray = userInput.split(" ");
        String action = commandArray[0].toUpperCase();
        switch (commandArray.length) {
            case 1:
                if (isValidMenuAction(action))
                    return new Command(VALID_MENU_ACTIONS.get(action));
                break;
            case 3:
                if (isValidGameCommand(action, commandArray)) {
                    Coordinate coordinate = parseCoordinate(commandArray);
                    return new GameCommand(VALID_GAME_ACTIONS.get(action), coordinate);
                }
                break;
            default:
                break;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Checks if the provided action string is a valid menu action.
     *
     * @param action The action string to check.
     * @return {@code true} if the action is valid; {@code false} otherwise.
     */
    private static boolean isValidMenuAction(String action) {
        return VALID_MENU_ACTIONS.containsKey(action);
    }

    /**
     * Checks if the provided input array represents a valid game command.
     *
     * @param action       The action string to check.
     * @param commandArray The input array containing the command parts.
     * @return {@code true} if the command is valid; {@code false} otherwise.
     */
    private static boolean isValidGameCommand(String action, String[] commandArray) {
        return (isValidGameAction(action) && isValidCoordinate(commandArray));
    }

    /**
     * Checks if the provided action string is a valid game action.
     *
     * @param action The action string to check.
     * @return {@code true} if the action is valid; {@code false} otherwise.
     */
    private static boolean isValidGameAction(String action) {
        return VALID_GAME_ACTIONS.containsKey(action);
    }

    /**
     * Parses the coordinates from the input array and returns a {@link Coordinate} object.
     *
     * @param commandArray The input array containing the command parts.
     * @return A {@link Coordinate} object representing the parsed coordinates.
     */
    public static Coordinate parseCoordinate(String[] commandArray) {
        int x = Integer.parseInt(commandArray[1]) - 1;
        int y = Integer.parseInt(commandArray[2]) - 1;
        return new Coordinate(x, y);
    }

    /**
     * Validates if the coordinates in the input array are within the allowed bounds.
     *
     * @param commandArray The input array containing the command parts.
     * @return {@code true} if the coordinates are valid; {@code false} otherwise.
     */
    private static boolean isValidCoordinate(String[] commandArray) {
        try {
            int x = Integer.parseInt(commandArray[1]);
            int y = Integer.parseInt(commandArray[2]);
            return (isWithinBounds(x, y));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the provided coordinates are within the set bounds.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return {@code true} if the coordinates are within bounds; {@code false} otherwise.
     */
    private static boolean isWithinBounds(int x, int y) {
        return x > 0 && y > 0 && x <= maxCoordinate.x() && y <= maxCoordinate.y();
    }

    /**
     * Sets the maximum allowed coordinate values.
     *
     * @param maxX The maximum x-coordinate value.
     * @param maxY The maximum y-coordinate value.
     */
    public static void setMaxCoordinate(int maxX, int maxY) {
        maxCoordinate = new Coordinate(maxX, maxY);
    }
}