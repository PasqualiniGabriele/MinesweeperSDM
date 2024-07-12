package cli;

import model.Board;
import de.vandermeer.asciitable.AsciiTable;
import model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class DisplayFormatter {

    protected String formatBoard(Board board) {
        int width = board.getWidth();
        int height = board.getHeight();
        AsciiTable at = new AsciiTable();
        at.addRule();
        for (int i = 0; i < width; i++) {
            List<String> columns = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                columns.add(board.getCell(new Coordinate(i, j)).toString());
            }
            at.addRow(columns);
            at.addRule();
        }
        return at.render();
    }

}


