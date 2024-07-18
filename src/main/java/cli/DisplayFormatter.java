package cli;

import model.Board;
import de.vandermeer.asciitable.AsciiTable;
import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment.CENTER;

public class DisplayFormatter {

    public static final String RESET = "\u001B[0m";
    public static final String IDK = "\u001B[33m";
    public static final String GREY = "\u001B[90m";

    protected static void displayWelcomeScreen() {
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

    protected static void displayMenu() {
        System.out.println("""
                =======================================================================================
                ||                                     MENU                                          ||
                =======================================================================================
                ||  1. Start new game                                                                ||
                ||  2. See game rules                                                                ||
                ||  3. Exit game                                                                     ||
                =======================================================================================
                """);
    }

    protected static void displayDifficultyMenu() {
        System.out.println("""
                =======================================================================================
                ||                             CHOOSE DIFFICULTY LEVEL                               ||
                =======================================================================================
                ||  1. Easy                                                                          ||
                ||  2. Medium                                                                        ||
                ||  3. Hard                                                                          ||
                =======================================================================================
                """);
    }

    protected static void displayGameRules() {
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
    }

    public static void displayBoard(Board board) {
        int width = board.getWidth();
        int height = board.getHeight();

        System.out.println("\n");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print( board.getCell(new Coordinate(x, y)) + "  ");
            }
            System.out.println(" |" + (y + 1));
        }
        for (int x = 0; x < width; x++) {
            System.out.print("_  ");
        }

        System.out.println();

        for (int x = 0; x < width; x++) {
            System.out.print((x + 1) + " ");
            if (x + 1 < 10)
                System.out.print(" ");
        }

        System.out.println("\n");
    }
}


