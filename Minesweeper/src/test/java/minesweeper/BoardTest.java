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
    }

    @Test
    public void constructorWorks() {
        Board clone = new Board(9, 9, 10);
        Board random = new Board(10, 10, 9);
        assertTrue(board.getWidth() == 9);
        assertTrue(board.getHeight() == 9);
        assertTrue(board.getMines() == 10);
        assertTrue(board.getSeed() >= 0);
        assertFalse(board.allCellsOpen());
        assertTrue(Arrays.deepEquals(board.getBoard(), clone.getBoard()));
        assertFalse(Arrays.deepEquals(board.getBoard(), random.getBoard()));
    }

    @Test
    public void generateSeedGeneratesRandomSeed() {
        assertFalse(board.generateSeed() == board.generateSeed()); // If this test fails, consider participating lottery.
    }

    @Test
    public void setSeedSetsSeed() {
        board.setSeed(1337);
        assertTrue(board.getSeed() == 1337);
    }

    /*@Test
    public void generateMinefieldGeneratesProperMinefield() {
        board.setSeed(1337);
        board.generateMinefield(0, 0);
        boolean[] mines = new boolean[81];
        for (char[] row : board.getBoard()) {
            for (char cell : row) {
                if (cell == '*') mines++;
            }
        }
        assertEquals(10, mines);
    }

    /*@Test
    public void openCellOpensCell() {
        board.openCell(6, 6);
        Cell[][] cells = board.getBoard();
        assertTrue(cells[6][6].getIsOpen());
    }*/
}
