package cli;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class DisplayFormatterTest {
    private Board board;
    private DisplayFormatter displayFormatter;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        displayFormatter = new DisplayFormatter();
    }

    @Test
    void printFormatBoard() {
        when(board.toString()).thenReturn("[[0,2,X],[0,3,X],[0,2,X]]");
        System.out.println(displayFormatter.formatBoard(board));
    }

}