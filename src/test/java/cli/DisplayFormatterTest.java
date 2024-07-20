package cli;

import handler.BoardManager;
import handler.BombPlacer;
import model.*;
import org.junit.jupiter.api.BeforeEach;

import static cli.ConsoleColor.removeAnsiCodes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class DisplayFormatterTest {
    private Board board;
    private BombPlacer bombPlacer;

    @BeforeEach
    void setUp() {
        BoardManager boardManager = new BoardManager(Configuration.EASY);
        board = boardManager.getBoard();
        bombPlacer = new BombPlacer(Configuration.EASY, board);

        setBombs(new Coordinate(0, 3), new Coordinate(4, 2),
                 new Coordinate(3, 0), new Coordinate(3, 6),
                 new Coordinate(6, 0), new Coordinate(1, 1),
                 new Coordinate(7, 2), new Coordinate(6, 4),
                 new Coordinate(5, 5), new Coordinate(5, 1));

        boardManager.applyClick(new Coordinate(3, 0));
        boardManager.applyClick(new Coordinate(6, 0));
        boardManager.applyClick(new Coordinate(2, 4));
        boardManager.applyFlag(new Coordinate(6, 7));
        boardManager.applyFlag(new Coordinate(1, 1));
    }

    private void setBombs(Coordinate... coordinates) {
        for (Coordinate coordinate : coordinates) {
            BombedCell bombedCell = new BombedCell();
            board.setCell(bombedCell, coordinate);
            bombPlacer.updateProximity(coordinate);
        }
    }


    @Test
    void testFormatBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DisplayFormatter.displayBoard(board);

        String expectedOutput = """
                ■  ■  ■  ●  ■  ■  ●  ■   |1
                ■  ⚑  ■  ■  ■  ■  ■  ■   |2
                ■  2  1  1  ■  ■  ■  ■   |3
                ■  1  -  1  1  ■  ■  ■   |4
                1  1  -  -  1  ■  ■  ■   |5
                -  -  1  1  2  ■  ■  ■   |6
                -  -  1  ■  ■  ■  ■  ■   |7
                -  -  1  ■  ■  ■  ⚑  ■   |8
                _  _  _  _  _  _  _  _ \s
                1  2  3  4  5  6  7  8 \s
                """;
        assertEquals(expectedOutput, removeAnsiCodes(outContent.toString()));
    }

}