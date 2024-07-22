package main;

import cli.CLIHandler;
import handler.game.GameController;


public class Minesweeper {
    public static void main(String[] args) {
        GameController minesweeper = new GameController(new CLIHandler());
        minesweeper.launch();
    }
}