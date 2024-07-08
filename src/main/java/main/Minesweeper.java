package main;

import cli.CLIHandler;
import cli.CommandParser;
import cli.DisplayFormatter;
import controller.GameController;

public class Minesweeper {
    public static void main(String[] args) {
        // factory?
        GameController gameController = new GameController();
        CommandParser commandParser = new CommandParser();
        DisplayFormatter displayFormatter = new DisplayFormatter();

        CLIHandler cliHandler = new CLIHandler(gameController, commandParser, displayFormatter);
        cliHandler.launch();
    }
}
