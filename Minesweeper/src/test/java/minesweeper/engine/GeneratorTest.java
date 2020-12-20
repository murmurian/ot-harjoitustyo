package minesweeper.engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void consturctorWorks() {
        Generator generator = new Generator();
        Cell[][] board = generator.generateBoard(13, 37);
        assertEquals(13, board[0].length);
        assertEquals(37, board.length);
        assertEquals(Cell.class, board[0][0].getClass());
    }

    @Test
    public void rightNumberOfMinesAreSet() {
        Generator generator = new Generator();
        Cell[][] board = generator.generateBoard(13, 37);
        generator.generateMinefield(5, 6, board, 18);
        int c = 0;
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getIsMine()) {
                    c++;
                }
            }
        }
        assertEquals(18, c);
    }
}
