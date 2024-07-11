package cli;

import controller.GameController;

import java.util.Scanner;

public class CLIHandler extends Handler {
    CommandParser commandParser;
    DisplayFormatter displayFormatter;
    Scanner scanner;

    public CLIHandler() {
        super();
    }

    public CLIHandler(GameController gameController, CommandParser commandParser, DisplayFormatter displayFormatter) {
        super(gameController);
        this.commandParser = commandParser;
        this.displayFormatter = displayFormatter;
        scanner = new Scanner(System.in);
    }

    @Override
    public void launch() {
        System.out.println("Welcome to Minesweeper!");
        System.out.println("""
                Menu:
                1. Start new game
                2. See game rules
                3. Exit game
                Choose an option:
                """);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                newGame();
                break;
            case "2":
                gameRules();
                break;
            case "3":
                exit();
                break;
            default:
                throw new IllegalArgumentException("Not a valid option");

        }
    }

    @Override
    protected void newGame() {
        setDifficulty();
    }

    private void setDifficulty() {
        System.out.println("""
                Choose difficulty:
                1. Easy
                2. Medium
                3. Hard""");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                gameController.createGame("EASY");
                break;
            case "2":
                gameController.createGame("MEDIUM");
                break;
            case "3":
                gameController.createGame("HARD");
                break;
            default:
                throw new IllegalArgumentException("Not a valid option");
        }
    }

    @Override
    protected void gameRules() {

    }

    @Override
    protected void exit() {
    }

    // for testing:
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
