package cli;

import model.Board;
import de.vandermeer.asciitable.AsciiTable;

public class DisplayFormatter {
    private String flagSymbol = "🚩";
    private String bombSymbol = "💣";

    protected String formatBoard(Board board) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(flagSymbol, "row 1 col 2");
        at.addRule();
        at.addRow("row 2 col 1", bombSymbol);
        at.addRule();
        return at.render();
    }
}

