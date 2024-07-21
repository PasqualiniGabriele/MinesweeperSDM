package cli;

import handler.Command;
import handler.UIHandler;
import model.Board;
import model.Configuration;
import model.GameStatus;

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
        DisplayFormatter.displayWelcomeScreen();
    }

    @Override
    public Configuration askForConfiguration() {
        DisplayFormatter.displayDifficultyMenu();
        String input = scanner.nextLine().trim();
        return switch (input) {
            case "1" -> Configuration.EASY;
            case "2" -> Configuration.MEDIUM;
            case "3" -> Configuration.HARD;
            default -> {
                System.out.println("\nUnknown command, try again.\n\n");
                yield askForConfiguration();
            }
        };
    }

    @Override
    public void show(String[] gameStats, Board board) {
        DisplayFormatter.displayTopMenu(gameStats);
        DisplayFormatter.displayBoard(board);
    }

    @Override
    public Command hasNextCommand() {
        DisplayFormatter.displayBottomMenu();
        String input = scanner.nextLine();
        try {
            return CommandParser.parseCommand(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command\nValid format: C x y or F x y");
            return hasNextCommand();
        }
    }

    @Override
    protected void gameRules() {
        DisplayFormatter.displayGameRules();
        scanner.nextLine();
    }

    @Override
    protected void exit(GameStatus gameStatus) {
        if (gameStatus == GameStatus.WON) displayWinMessage();
        else if (gameStatus == GameStatus.LOST) displayLostMessage();
    }

    @Override
    public boolean isNewGameRequested() {
        System.out.println("\nWant to play again? (y/n)");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}