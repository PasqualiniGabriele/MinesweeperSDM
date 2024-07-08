package cli;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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