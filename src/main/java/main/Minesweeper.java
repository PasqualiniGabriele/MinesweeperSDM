package main;
import cli.CLIHandler;
import cli.Handler;


public class Minesweeper {
    public static void main(String[] args) {
        Handler handler = new CLIHandler();
        handler.launch();
    }
}
