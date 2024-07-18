package cli;

import model.*;
import org.junit.jupiter.api.BeforeEach;

import static cli.DisplayFormatter.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class DisplayFormatterTest {
    private Board board;

    @BeforeEach
    void setUp() {
        Cell cellToFlag = new FreeCell(1);
        Cell freeCellToReveal = new FreeCell(1);
        Cell bombedCellToReveal = new BombedCell();
        cellToFlag.toggleFlag();
        freeCellToReveal.reveal();
        bombedCellToReveal.reveal();
        board = new Board(2, 2);
        board.setCell(new FreeCell(0), new Coordinate(0, 0));
        board.setCell(freeCellToReveal, new Coordinate(0, 1));
        board.setCell(cellToFlag, new Coordinate(1, 0));
        board.setCell(bombedCellToReveal, new Coordinate(1, 1));
    }



    @Test
    void testFormatBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DisplayFormatter.displayBoard(board);


        String expectedOutput =
                              "\n\n" + GREY + "■" + RESET +  "  ⚑  " + GREY + " |1" + RESET +
                                  "\n" + BLUE + "1" + RESET + "  ✷  " + GREY + " |2" + RESET + "\n" +
                            GREY + "_  _  " +
                                   "\n1  2  \n" + RESET + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}