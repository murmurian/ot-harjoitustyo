package minesweeper;

import minesweeper.engine.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board(9, 9, 10);
        board.setSeed(1337);
        board.generateMinefield(0, 0);
    }

    @Test
    public void constructorWorks() {
        Board clone = new Board(9, 9, 10);
        Board random = new Board(10, 10, 9);
        assertTrue(board.getWidth() == 9);
        assertTrue(board.getHeight() == 9);
        assertTrue(board.getMines() == 10);
        assertFalse(board.allCellsOpen());
        assertTrue(Arrays.deepEquals(board.getBoard(), clone.getBoard()));
        assertFalse(Arrays.deepEquals(board.getBoard(), random.getBoard()));
    }

    @Test
    public void generateMinefieldGeneratesProperMinefield() {
        board.openMines();
        assertTrue(board.getBoard()[0][3] == '*');
        assertTrue(board.getBoard()[0][8] == '*');
        assertTrue(board.getBoard()[1][0] == '*');
        assertTrue(board.getBoard()[4][5] == '*');
        assertTrue(board.getBoard()[5][1] == '*');
        assertTrue(board.getBoard()[6][4] == '*');
        assertTrue(board.getBoard()[7][3] == '*');
        assertTrue(board.getBoard()[7][3] == '*');
        assertTrue(board.getBoard()[7][5] == '*');
        assertTrue(board.getBoard()[8][5] == '*');
        for (int i = 0; i < 9; i++) {
            assertFalse(board.getBoard()[3][i] == '*');
        }
    }

    @Test
    public void noMineOnFirstClick() {
        board = new Board(9, 9, 10);
        board.setSeed(1337);
        board.generateMinefield(0, 1);
        board.openCell(1, 0);
        assertFalse(board.getBoard()[1][0] == '*');        
    }

    @Test
    public void setMinesNearWorks() {
        board.openCell(4, 8);
        board.openCell(5, 5);
        assertTrue(board.getBoard()[5][5] == '2');
        assertTrue(board.getBoard()[8][4] == '4');
    }

    @Test
    public void openCellRangesWork() {
        board.openCell(-1, -1);
        board.openCell(9, 9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals('#', board.getBoard()[j][i]);
            }
        }
    }

    @Test
    public void openCellSetsGameOver() {
        board.openCell(0, 1);
        assertTrue(board.getMineHit());
    }
}
