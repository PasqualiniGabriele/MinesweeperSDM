package main;
import cli.CLIHandler;
import handler.GameController;


public class Minesweeper {
    public static void main(String[] args) {
        GameController minesweeper = new GameController(new CLIHandler());
        minesweeper.launch();
    }
}
