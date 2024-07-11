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
        displayWelcomeScreen();
        try {
            menu();
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command.\nReturning to main menu...");
            menu();
        }
    }

    private static void displayWelcomeScreen() {
        System.out.println("""
                                 _
                     ____ ___   (_)____   ___   _____ _      __ ___   ___   ____   ___   _____
                    / __ `__ \\ / // __ \\ / _ \\ / ___/| | /| / // _ \\ / _ \\ / __ \\ / _ \\ / ___/
                   / / / / / // // / / //  __/(__  ) | |/ |/ //  __//  __// /_/ //  __// /
                  /_/ /_/ /_//_//_/ /_/ \\___/ \\___/  |__/|__/ \\___/ \\___// .___/ \\___//_/
                                                                        /_/
                """);
        System.out.println("""
                =======================================================================================
                ||                                WELCOME TO MINESWEEPER!                            ||
                ||               Uncover all the mines without triggering any explosions.            ||
                ||                                                                                   ||
                ||    Instructions:                                                                  ||
                ||    - Use numbers to navigate the menu options.                                    ||
                ||    - Confirm your choice by pressing 'Enter'.                                     ||
                ||    - Review game rules to learn how to play effectively.                          ||
                ||                                                                                   ||
                ||                              Good luck, and have fun!                             ||
                =======================================================================================
                """);
    }

    private void menu() {
        System.out.println("""
            ===========================================================================================
            ||                                     MENU                                              ||
            ===========================================================================================
            ||  1. Start new game                                                                    ||
            ||  2. See game rules                                                                    ||
            ||  3. Exit game                                                                         ||
            ===========================================================================================
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
                throw new IllegalArgumentException();
        }
    }

    @Override
    protected void newGame() {
        setDifficulty();

    }

    private void setDifficulty() {
        System.out.println("""
            ===========================================================================================
            ||                             CHOOSE DIFFICULTY LEVEL                                   ||
            ===========================================================================================
            ||  1. Easy                                                                              ||
            ||  2. Medium                                                                            ||
            ||  3. Hard                                                                              ||
            ||                                                                                       ||
            ||  Press any other key to return to the main menu.                                      ||
            ===========================================================================================
            """);

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
                menu();
        }
    }


    @Override
    protected void gameRules() {
        System.out.println("""
            ===========================================================================================
            ||                                MINESWEEPER GAME RULES                                 ||
            ===========================================================================================
            ||  Objective:                                                                           ||
            ||  - Uncover all cells without triggering any bombs.                                    ||
            ||                                                                                       ||
            ||  How to Play:                                                                         ||
            ||  - The game board consists of cells. Each cell can either be uncovered or flagged.    ||
            ||  - Uncover a cell ('C x y'): Reveals the content of the cell at coordinates (x, y).   ||
            ||  - Flag or unflag a cell ('F x y'): Marks or unmarks a cell as a potential bomb.      ||
            ||                                                                                       ||
            ||  Game Commands:                                                                       ||
            ||  - To uncover a cell: Type 'C x y', where (x, y) are the coordinates of the cell.     ||
            ||  - To flag/unflag a cell: Type 'F x y', where (x, y) are the coordinates of the cell. ||
            ||  - Coordinates are 1-based and should be within the dimensions of the game board.     ||
            ||                                                                                       ||
            ||  Game Over:                                                                           ||
            ||  - The game ends if you uncover a cell containing a bomb.                             ||
            ||                                                                                       ||
            ||  Difficulty Levels:                                                                   ||
            ||  - Easy: 9x9 board with 10 bombs.                                                     ||
            ||  - Medium: 16x16 board with 40 bombs.                                                 ||
            ||  - Hard: 16x30 board with 99 bombs.                                                   ||
            ||                                                                                       ||
            ||  Press any other key to return to the main menu.                                      ||
            ===========================================================================================
            """);
        scanner.nextLine();
        menu();
    }

    @Override
    protected void exit() {
    }

    // for testing:
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
