package main;

import cli.CLIHandler;
import handler.game.GameController;

/**
 * The entry point for the Minesweeper game application.
 */
public class Minesweeper {

    /**
     * The main method to launch the Minesweeper game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        GameController minesweeper = new GameController(new CLIHandler());
        minesweeper.launch();
    }
}