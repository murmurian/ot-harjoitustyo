package minesweeper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void newCellIsNotOpen() {
        assertFalse(cell.getIsOpen());
    }

    @Test
    public void newCellIsNotMine() {
        assertFalse(cell.getIsMine());
    }

    @Test
    public void newCellIsNotFlagged() {
        assertFalse(cell.getIsFlagged());
    }

    @Test
    public void openedCellIsOpen() {
        cell.setIsOpen();
        assertTrue(cell.getIsOpen());
    }

    @Test
    public void setMineSetsMine() {
        cell.setIsMine();
        assertTrue(cell.getIsMine());
    }

    @Test
    public void setFlaggedFlagsCell() {
        cell.setIsFlagged();
        assertTrue(cell.getIsFlagged());
    }

    @Test
    public void newCellHasZeroMinesNear() {
        assertEquals(0, cell.getMinesNear());
    }

    @Test
    public void addMinesAddsMine() {
        cell.addMinesNear();
        assertEquals(1, cell.getMinesNear());
    }
}
