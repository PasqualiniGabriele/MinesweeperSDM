package cli;

import handler.UIHandler;
import model.Board;
import model.Configuration;

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
        DisplayFormatter.displayBottomMenu();
        String input = scanner.nextLine();
        if (isQuitCommand(input)) {
            return null;
        }
        try {
            return CommandParser.parseCommand(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command\nValid format: C x y or F x y");
            return hasNextCommand();
        }
    }

    @Override
    public Configuration askForDifficulty() {
        DisplayFormatter.displayDifficultyMenu();
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> Configuration.EASY;
            case "2" -> Configuration.MEDIUM;
            case "3" -> Configuration.HARD;
            default -> Configuration.MEDIUM;
        };
    }

    @Override
    public void show(String[] gameStats, Board board) {
        DisplayFormatter.displayTopMenu();
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