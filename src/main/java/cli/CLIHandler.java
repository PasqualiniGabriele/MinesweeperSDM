package cli;

import controller.GameController;

import java.util.Scanner;

public class CLIHandler extends Handler {
    CommandParser commandParser;
    DisplayFormatter displayFormatter;

    public CLIHandler() {
        super();
    }

    public CLIHandler(GameController gameController, CommandParser commandParser, DisplayFormatter displayFormatter) {
        super(gameController);
        this.commandParser = commandParser;
        this.displayFormatter = displayFormatter;
    }

    @Override
    public void launch() {
        System.out.println("Welcome to Minesweeper!");
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
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
                    running = false;
                    break;
            }
        }
    }

    @Override
    protected void newGame() {
    }

    @Override
    protected void setDifficulty() {
    }

    @Override
    protected void gameRules() {

    }

    @Override
    protected void exit() {
    }

}
