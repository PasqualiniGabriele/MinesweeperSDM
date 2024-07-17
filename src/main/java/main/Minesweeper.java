package main;
import cli.CLIHandler;
import handler.UserHandler;


public class Minesweeper {
    public static void main(String[] args) {
        UserHandler handler = new CLIHandler();
        handler.launch();
    }
}
