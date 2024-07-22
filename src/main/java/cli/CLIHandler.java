package cli;

import handler.input.Command;
import handler.input.UIHandler;
import model.board.Board;
import model.board.Configuration;
import model.game.GameStatus;

import java.util.Scanner;

import static cli.DisplayFormatter.*;

public class CLIHandler extends UIHandler {
    Scanner scanner;

    public CLIHandler() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void welcome() {
        clearScreen();
        displayWelcomeScreen();
        scanner.nextLine();
        clearScreen();
    }

    @Override
    public Configuration askForConfiguration() {
        displayDifficultyMenu();
        String input = scanner.nextLine().trim();
        return switch (input) {
            case "1" -> Configuration.EASY;
            case "2" -> Configuration.MEDIUM;
            case "3" -> Configuration.HARD;
            default -> {
                displayErrorMessage("Unknown command, try again.");
                yield askForConfiguration();
            }
        };
    }

    @Override
    public void show(String[] gameStats, Board board) {
        clearScreen();
        displayTopMenu(gameStats);
        displayBoard(board);
    }

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

    @Override
    public void gameRules() {
        displayGameRules();
        scanner.nextLine();
    }

    @Override
    public void exit(GameStatus gameStatus) {
        if (gameStatus == GameStatus.WON) displayWinMessage();
        else if (gameStatus == GameStatus.LOST) displayLostMessage();
        sleepFor(20);
    }

    @Override
    public boolean isNewGameRequested() {
        System.out.println("\nWant to play again? (y/n)");
        String input = scanner.nextLine().trim().toUpperCase();
        clearScreen();
        return input.equals("Y");
    }

    @Override
    public void printWrongCoordinateError() {
        System.out.println("Coordinates exceeded the board limits");
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}