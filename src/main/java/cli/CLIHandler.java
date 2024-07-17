package cli;

import handler.UserHandler;

import java.util.Scanner;

public class CLIHandler extends UserHandler {
    Scanner scanner;
    private static final String QUIT_COMMAND = "Q";

    public CLIHandler() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void launch() {
        DisplayFormatter.displayWelcomeScreen();
        try {
            menu();
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command.\nReturning to main menu...");
            menu();
        }
    }

    private void menu() {
        DisplayFormatter.displayMenu();
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                setDifficulty();
                startGame();
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

    protected void setDifficulty() {
        DisplayFormatter.displayDifficultyMenu();

        String input = scanner.nextLine();
        switch (input) {
            case "1":
                getGameController().createGame("EASY");
                break;
            case "2":
                getGameController().createGame("MEDIUM");
                break;
            case "3":
                getGameController().createGame("HARD");
                break;
        }
    }

    @Override
    protected void startGame() {
        while (true) {
            DisplayFormatter.displayBoard(getGameController().getBoard());

            System.out.println("Enter next command or Q to quit game:");
            String input = scanner.nextLine();
            if (isQuitCommand(input)) {
                exit();
                return;
            } else {
                processCommand(input);
            }
        }
    }

    private static boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }

    private void processCommand(String input) {
        try {
            getGameController().applyCommand(CommandParser.parseCommand(input));
        } catch (IllegalArgumentException e) {
            System.out.println("""
                    Not a valid command:
                    Game Commands:
                    - To uncover a cell: Type 'C x y'
                    - To flag/unflag a cell: Type 'F x y'
                    - where (x, y) are the coordinates of the cell.
                    """);
        }
    }

    @Override
    protected void gameRules() {
        DisplayFormatter.displayGameRules();

        scanner.nextLine();
        menu();
    }

    @Override
    protected void exit() {
        System.out.println("Thank you for playing!");
    }

    // for testing:
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
