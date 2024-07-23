package cli;

import model.board.Board;
import model.board.Coordinate;

import static cli.ConsoleColor.*;
import static java.lang.Thread.sleep;

public class DisplayFormatter {

    protected static void displayWelcomeScreen() {
        System.out.print("""
                
                +-------------------------------------------------------------------------------------+
                ||       ____ ___   (_)____   ___   _____ _      __ ___   ___   ____   ___   _____   ||
                ||      / __ `__ \\ / // __ \\ / _ \\ / ___/| | /| / // _ \\ / _ \\ / __ \\ / _ \\ / ___/   ||
                ||     / / / / / // // / / //  __/(__  ) | |/ |/ //  __//  __// /_/ //  __// /       ||
                ||    /_/ /_/ /_//_//_/ /_/ \\___/ \\___/  |__/|__/ \\___/ \\___// .___/ \\___//_/        ||
                ||                                                          /_/                      ||
                ||             Uncover all the mines without triggering any explosions.              ||
                |_____________________________________________________________________________________|
                |                                                                                     |
                |     Instructions:                                                                   |
                |       - Use numbers to navigate the menu options.                                   |
                |       - Confirm your choice by pressing 'Enter'.                                    |
                |       - Review game rules to learn how to play effectively.                         |
                |                                                                                     |
                |                              Good luck, and have fun!                               |
                +-------------------------------------------------------------------------------------+
                
                Press any key to continue:\s""");
    }

    protected static void displayDifficultyMenu() {
        System.out.print("""
                
                +-------------------------------------------------------------------------------------+
                ||                             CHOOSE DIFFICULTY LEVEL                               ||
                |_____________________________________________________________________________________|
                |    1. Easy                                                                          |
                |    2. Medium                                                                        |
                |    3. Hard                                                                          |
                +-------------------------------------------------------------------------------------+

                Level:\s""");
    }

    protected static void displayTopMenu(String[] gameStats) {
        System.out.println("\n_____________________________________ MINESWEEPER______________________________________\n");
        System.out.println("Level: " + GREY + gameStats[0] + RESET + "\t Flags: " + RED + gameStats[1] + RESET + "\t Time: " + GREY + gameStats[2] + RESET + "\n");
    }

    protected static void displayBottomMenu() {
        System.out.println("\n" + WHITE_BG + " I " + RESET + " Game Rules   " + WHITE_BG + " Q " + RESET + " Exit   ");
        System.out.print("\nCommand:  ");
    }

    protected static void displayGameRules() {
        System.out.println("""
                +-------------------------------------------------------------------------------------------+
                ||                                MINESWEEPER GAME RULES                                   ||
                |___________________________________________________________________________________________|
                |-------------------------------------------------------------------------------------------|
                |   Objective:                                                                              |
                |       - Uncover all cells without triggering any bombs.                                   |
                |                                                                                           |
                +-------------------------------------------------------------------------------------------+
                |                                                                                           |
                |   How to Play:                                                                            |
                |       - The game board consists of cells. Each cell can either be uncovered or flagged.   |
                |       - Uncover a cell ('C x y'): Reveals the content of the cell at coordinates (x, y).  |
                |       - Flag or unflag a cell ('F x y'): Marks or unmarks a cell as a potential bomb.     |
                |                                                                                           |
                +-------------------------------------------------------------------------------------------+
                |                                                                                           |
                |   Game Commands:                                                                          |
                |       - To uncover a cell: Type 'C x y', where (x, y) are the coordinates of the cell.    |
                |       - To flag/unflag a cell: Type 'F x y', where (x, y) are the coordinates of the cell.|
                |       - Coordinates are 1-based and should be within the dimensions of the game board.    |
                |                                                                                           |
                +-------------------------------------------------------------------------------------------+
                |                                                                                           |
                |   Game Over:                                                                              |
                |       - The game ends if you uncover a cell containing a bomb.                            |
                |                                                                                           |
                +-------------------------------------------------------------------------------------------+
                |                                                                                           |
                |   Difficulty Levels:                                                                      |
                |       - Easy: 9x9 board with 10 bombs.                                                    |
                |       - Medium: 16x16 board with 40 bombs.                                                |
                |       - Hard: 16x30 board with 99 bombs.                                                  |
                |                                                                                           |
                |   Press any other key to return to the main menu.                                         |
                +-------------------------------------------------------------------------------------------+
                """);
    }

    public static void displayBoard(Board board) {
        int width = board.getWidth();
        int height = board.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(board.getCell(new Coordinate(x, y)) + "  ");
            }
            System.out.println(GREY + " |" + (y + 1) + RESET);
        }
        for (int x = 0; x < width; x++) {
            System.out.print(GREY + "_  ");
        }
        System.out.println();

        for (int x = 0; x < width; x++) {
            System.out.printf("%-2d ", x + 1);
        }
        System.out.println(RESET);
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void sleepFor(int deciSeconds){
        try {
            sleep(deciSeconds*100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayErrorMessage(String message){
        System.out.println(DARK_GREY + "\n--" + RED + "ERROR" + DARK_GREY + " -------------------------------------------------------------------------------");
        System.out.println("\t\t\t" + RESET + message + DARK_GREY);
        System.out.println("---------------------------------------------------------------------------------------" + RESET);
    }

    protected static void displayWinMessage() {
        System.out.println("""

                          ░░▒▒▓▓▒                                            ░▒▒▒▒▒░
                       ░░▓▓▓▒▒░  ▓▒▓▓▓▓▓▓▒▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒░▒▓▓▒▒▒░ ░▒▒
                      ▒▒▓▓░    ▒▒▒▒▒░░▒▓▓▓▓▓▓▓█▒░░░░░░▒░░░░░░░          ░▒▒▓▓░    ▒▒  ▒
                     ░▒░░░  ░▒▒░░░░▒░░▓▒▒▒▒▒▒▒░░░▒▒░░░▒▒░░░░░▒▒▒▒▒▒      ▒░░░░░▒░  ░▒░░░
                     ░▒ ▒   ▒▓█▓▓▓▒▒░░▒▒▒▒▒▒▒░░▒▒▒▒▒░░░░░▒░░░░░░░░▒▒░   ▒▒▒▒▒░░▒▒   ░░░░
                     ░▒ ░░    ▒▒▒  ▒▒░▒▒▒▒▒▒░░░▒▒▒▒▒▒░░░░░░░░░░░░░░▒▒▒▒▒▒▒  ▒▒▒     ░▒▓░
                      ▒▒▒▒         ▒▒░▒▒▒▒▒▒░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░▒▒         ░▒▓▒
                       ▒▒▒▒▓▒       ▒░▒▒▒▒▒▒▒░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░▒     ░░▒▒▒▓▓▒
                         ░▓▓▓▓▓▓▓▓▒ ░▒░▒▒▒▒▒▒▒░░░░▒▒▒▒░░░░░░░░░░░░░░░░░░░ ░▒▒▒▒▒▒▒▒░
                               ░▒▓▓▓▒░░░▒▒▒▒▒▒▓░░░░░▒▒▒▒░░░░░░▒▒▒▒▒▓▓▒░░░▓▓▓▒░
                                   ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒░▓▒░
                                   ▒▓▓  ▓▒▒▒▒▒▒▒▒░░░▒▒▒░░░▒▒▒▒▒▒▒▒▒░▒░ ▒▓▒
                                 ▓▓▓▓    ░▓▓▒▒▓▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░    ▒▓▒▓
                                             ▒▓▒▒▒▒▒▓▓▓▓▓▓▒░▒▒▒▒
                                                 ░░░░░░░░░░▒
                                                 ▓▓█████▓▒▓▒
                                                   ░░░░░░▒░
                                                   ▒░▓▓▓▒▒
                                                ░▒▒▒▒░░░▒▒▓▓▒
                                               ▓▒▓▓▓▒░░▒    ▒▓░
                                             ░▒▒▒▒▒▒░▒░░░  ░▒▒▒░
                                             ░░░░░░░░░░░░░░░░░░░

                        ██╗   ██╗ ██████╗ ██╗   ██╗   ██╗    ██╗ ██████╗ ███╗   ██╗  ██╗
                        ██║   ██║██║   ██╗██║   ██║   ██║    ██║██║   ██╗████╗  ██║  ██║
                        ╚██████╔╝██║   ██║██║   ██║   ██║ █╗ ██║██║   ██║██╔██╗ ██║  ██║
                          ╚██╔═╝ ██║   ██║██║   ██║   ██║███╗██║██║   ██║██ ╚██╗██║  ╚═╝
                           ██║   ╚██████╔╝╚██████╔╝   ╚███╔███╔╝╚██████╔╝██║ ╚████║  ██╗
                           ╚═╝    ╚═════╝  ╚═════╝     ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝  ╚═╝
                """);
        sleepFor(10);
    }

    protected static void displayLostMessage() {
        System.out.println("""
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣴⣶⢶⣦⣤⡾⠋⠑⠁⣀⡙⢷⡤⠖⠲⠶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣶⠶⠟⢳⣟S⡵⢾⣻⣯⣷⣶⣦⣌⠉⠛⠿⢿⣦⣀⠀⠈⠹⣷⡒⠒⠶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡶⠟⡽⠋⢀⣴⣶⢿⡏⠉⢃⣬⡟⠁⠀⠹⠀⢽⣿a⠞⠋⠉⢉⣷⠦⣤⠿⣍⣗⣦⠈⢻⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⣠⣴⣾⡻⡟⣠⠦⣤⡀⣉⡾⠉⣠⡴⣿l⠀⠁⣀⣀⡟⠙⢿⣇⡈⠇⠀⠀⠈⡿⢮⣉⠉⠉⠙⣾⢦⡈⡿⠡⣤⡈⠻⣦⡀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⣾⣯⣽⡟⢳⡏⠹⠄⠀⡞⠁⠀⠾⠉⢳⡟⢉⣬⣿⠁⠈⠃⠀⠉⠀⣻a⠀⠀⠀⠀⠰⠏⠿⠀⣠⢯⣀⠀⠀⠀⣈⣿⣄⣿⠻⣦⡀⠀⠀
                         ⠀⠀⢠⡾⡿⠿⠀⣥⠌⢁⣰⣤⡼⠛⠒⠀⠀⠀⠈⠳⠍m⣹⠆⠀⠀⠀⠀⠀⠈⢙⡆⠀⠀⣀⠀⠀⠀⠀⠁ ⠀⢸⡗⢲⣦⣬⠚⠋⠉⣆⠈⣷⡀⠀
                         ⠀⠀⣿⡇⢠⡶⠀⣷⣤⣾⠇⠉⢸⡯⠀⠀⠀⠀⡴⠖⠀⠈⠻⣀⣀⡄⠀⠀⢀⣀⢉u⠀⠛⠉⣹⣦⡀⠀⠀⠐⠋⢩⣛⡃⠉⠉⣻⣀⡼⡿⠋⢻⣆
                         ⢀⣼⣿⣿⣾⣧⣴⠏⠀⠀⠀⠀⠺⣄⣀⠀⢀⣈⡳⣄⠤⢄⣴c⠃⠀⣠⠗⠿⠓⠶⠛⣶⣤⣀⡀⠘⠯⢤⣚⢳⣤⣄⠨⠿⢦⣄⠀⠈⡷⠒⣄⢐⣿
                         ⢸⣿⣟⠙⢛⣧⢿⣄⠀⠀⠀⠀⢀⡬⠉⢱⣿⠁⠀⢀⣤⠟⠙⢿⣀⣠⠙⠶⠶⢤⠲⣤c⣘⠋⠀⠀⠀⠀⢹⠹⠿⣿⣀⢀⣀⣽⠙⠛⠑⣶⣿⣿⠋
                         ⠀⣿⡿⡍⣹⡇⣠⣾⣿⠀⣀⡀⣿⡟⣀⠾⢯⡉⢠⡿⠏⣦⣄⢀i⣥⡖⠀⠀⢸⡆⠀⠀⢙⡓⠶⢦⣴⠖⠋⠀⠀⠀⣟⣿⠋⠐⠒⣶⢛⣿⡇⢸⠇
                         ⠀⠿⣷⣾⣿⣻⡾⠓⠊⠉⠁⠀⠈⠉⠁⢠⡄⢠⡄⠀⢀⣼⣿⣿⡿⢻⡟⣶⣶⣿⣷⣤⣤o⣷⡀⣠⡏⠀⠀⢀⠀⠀⢹⡇⠀⣀⠒⠛⠰⣿⣾⠟⠀
                         ⠀⠀⠈⠻⣯⣬⣳⠶⣞⠀⠀⡀⠀⠀⠀⠈⠛⠚⠋⠙⠛⢿⣿⣿⣧⢀⢸⢡⢹⡎⠙⣿⣿⣧⡽⠛⠋⠀⠀⠀⠈⠓⠞⡋⠑⣶⠿⠀⠚⡟⠛⠁⠀⠀
                         ⠀⠀⠀⠀⠈⠹⣄⡀⠘⠲⠖⠳⡴⠂⠀⠀⠈⣿⣀⡀⠶⢾⠿⣿⣿⢸⢸⢸⢸⡇⠀⣾⣿⠏⠁⠀⣄⠀⣄⠀⣠⠄⠀⠉⡓⠉⠀⠀⣶⡇⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠈⠛⠶⠶⠶⣶⠀⠀⠀⠀⢀⡀⠉⠙⠀⠀⠀⣿⣿⢸⢸⢸⢸⡇⠀⡇⣿⠀⠀⠀⠈⠋⠉⠉⠉⠀⠀⣰⡟⠶⠾⠿⠋⠀⠀⠀⠀⠀
                         ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗      ██████╗ ███████╗████████╗
                         ██║   ██║██║   ██╗██║   ██║    ██║     ██║   ██╗██╔════╝╚══██╔══╝
                         ╚██████╔╝██║   ██║██║   ██║    ██║     ██║   ██║███████╗   ██║
                           ╚██╔═╝ ██║   ██║██║   ██║    ██║     ██║   ██║╚════██║   ██║
                            ██║   ╚██████╔╝╚██████╔╝    ███████╗╚██████╔╝███████║   ██║
                            ╚═╝    ╚═════╝  ╚═════╝     ╚══════╝ ╚═════╝ ╚══════╝   ╚═╝⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⢺⠉⢿⣿⢻⣟⣿⠶⠄⠃⠀⠈⠳⢦⡈⠃⠀⢷⡄⠰⣾⣏⢼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢷⡼⣯⡎⠁⢽⡁⢀⡀⣄⣀⣴⠼⢯⣀⣨⣿⣁⡀⣭⣤⡿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢮⣽⣷⣶⡉⢿⣶⣜⣿⣥⣴⣄⡉⣽⣾⣿⡿⠗⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢿⣿⢿⣿⡿⠿⠿⣿⣿⣏⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣿⣿⣧⣾⣿⣧⣤⣤⣍⡀⠹⣠⣴⡿⢿⣯⣿⣿⣷⣦⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣟⣿⣟⣿⣻⣿⣿⢛⣿⣽⣿⣿⣻⣶⢿⣿⣿⣾⣿⣾⣿⣷⣌⣙⣿⢻⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⡿⣿⣋⣽⣿⣿⡿⣿⡿⠛⢛⣟⢿⣿⣷⣾⣟⣿⣿⣯⣟⠿⠛⢿⣯⠉⢻⣿⢻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⢿⣿⢿⣿⣻⡿⠻⢿⡿⢷⢿⣿⢿⠀⠋⠹⠗⡿⣫⣭⣬⣸⠟⡷⠿⢿⢧⣴⠛⠛⠻⣾⠋⣿⣽⣷⠀⠀⠀⠀⠀⠀⠀⠀
                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠈⠁⠈⠀⠁⠀⠀⠈⠁⠀⠈⠻⠟⠳⠄⠀⠀⢧⠉⠹⠋⠘⠀⠀⠀⠀⠘⠁⠀⠀⠀⣿⠶⠟⠃⠙⣷⡦⠀⠀⠀⠀
                """);
        sleepFor(10);
    }
}



