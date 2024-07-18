package cli;

import de.vandermeer.asciitable.AsciiTable;
import model.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class DisplayFormatterTest {
    private Board board;
    private DisplayFormatter displayFormatter;

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
        displayFormatter = new DisplayFormatter();
    }


    @Test
    void testFormatBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        displayFormatter.displayBoard(board);

        String expectedOutput =
                """
                
                
                ■  ⚑   |1
                1  ✷   |2
                _  _ \s
                1  2 \s
                
                """;

        assertEquals(expectedOutput, outContent.toString());
    }

}