package cli;

import handler.input.Command;
import handler.input.UIHandler;
import model.board.Board;
import model.board.Configuration;
import model.game.Game;

import java.util.Scanner;

import static cli.DisplayFormatter.*;

/**
 * The {@code CLIHandler} class is responsible for handling user interactions in a command-line interface (CLI)
 * environment. It extends the {@link UIHandler} class and provides implementations for user interface operations
 * such as displaying messages, getting user input, and processing commands.
 */
public class CLIHandler extends UIHandler {
    Scanner scanner;

    /**
     * Constructs a {@code CLIHandler} instance.
     * Initializes a {@link Scanner} object for user input.
     */
    public CLIHandler() {
        super();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message and clears the screen after the user presses Enter.
     */
    @Override
    public void renderWelcomeScreen() {
        clearScreen();
        displayWelcomeScreen();
        scanner.nextLine();
        clearScreen();
    }

    /**
     * Prompts the user to select a game configuration and returns the chosen {@link Configuration}.
     * The method displays a difficulty menu and parses the user's input.
     *
     * @return The selected {@link Configuration}.
     */
    @Override
    public Configuration askConfigurationToUser() {
        displayDifficultyMenu();
        String input = scanner.nextLine().trim();
        Configuration configuration = parseConfiguration(input);
        CommandParser.setMaxCoordinate(configuration.getWidth(), configuration.getHeight());
        return configuration;
    }

    /**
     * Parses the user's input to determine the game configuration.
     *
     * @param input The user's input as a string.
     * @return The corresponding {@link Configuration}.
     */
    private Configuration parseConfiguration(String input) {
        return switch (input) {
            case "1" -> Configuration.EASY;
            case "2" -> Configuration.MEDIUM;
            case "3" -> Configuration.HARD;
            default -> {
                displayErrorMessage("Unknown command, try again.");
                yield askConfigurationToUser();
            }
        };
    }

    /**
     * Displays game statistics and the game board.
     *
     * @param gameStats An array of strings representing the game statistics.
     * @param board     The current {@link Board} to be displayed.
     */
    @Override
    public void renderGameScreen(String[] gameStats, Board board) {
        clearScreen();
        displayTopMenu(gameStats);
        displayBoard(board);
    }

    /**
     * Prompts the user for the next command and returns the parsed {@link Command}.
     * The method handles invalid input by displaying an error message and re-prompting the user.
     *
     * @return The parsed {@link Command}.
     */
    @Override
    public Command hasNextCommand() {
        displayBottomMenu();
        String input = scanner.nextLine();
        clearScreen();
        try {
            return CommandParser.parseCommand(input);
        } catch (IllegalArgumentException e) {
            displayErrorMessage("Not a valid command.\n\t\t\tValid format: C x y or F x y");
            return hasNextCommand();
        }
    }

    /**
     * Displays the game rules and waits for the user to press Enter.
     */
    @Override
    public void gameRules() {
        displayGameRules();
        scanner.nextLine();
    }

    /**
     * Displays a message based on the game's outcome.
     *
     * @param status The {@link Game.Status} representing the outcome of the game.
     */
    @Override
    public void exit(Game.Status status) {
        if (status == Game.Status.WON) displayWinMessage();
        else if (status == Game.Status.LOST) displayLostMessage();
    }


    /**
     * Prompts the user to start a new game.
     *
     * @return {@code true} if the user wants to play again; {@code false} otherwise.
     */
    @Override
    public boolean isNewGameRequested() {
        System.out.println("\nWant to play again? (y/n)");
        String input = scanner.nextLine().trim().toUpperCase();
        clearScreen();
        return input.equals("Y");
    }
}