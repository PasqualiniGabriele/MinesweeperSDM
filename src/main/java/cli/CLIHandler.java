package cli;

import handler.UIHandler;
import model.Board;
import model.Difficulty;

import java.util.Scanner;

public class CLIHandler extends UIHandler {
    Scanner scanner;
    private static final String QUIT_COMMAND = "Q";

    public CLIHandler() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void welcome() {
        DisplayFormatter.displayWelcomeScreen();
    }

    @Override
    public Command hasNextCommand() {
        String input = scanner.nextLine();
        if (isQuitCommand(input)) {
            return null;
        }
        return CommandParser.parseCommand(input);
    }

    @Override
    public Difficulty askForDifficulty() {
        DisplayFormatter.displayDifficultyMenu();
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> Difficulty.EASY;
            case "2" -> Difficulty.MEDIUM;
            case "3" -> Difficulty.HARD;
            default -> Difficulty.MEDIUM;
        };
    }

    @Override
    public void show(Board board) {
        DisplayFormatter.displayBoard(board);
    }

    @Override
    protected void gameRules() {
        DisplayFormatter.displayGameRules();
        scanner.nextLine();
    }

    @Override
    public void exit() {
        System.out.println("Thank you for playing!");
    }

    // for testing:
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private static boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }

}