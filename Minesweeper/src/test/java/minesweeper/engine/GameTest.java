package minesweeper.engine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setUp() {
        game = new Game(0);
        game.useSeedValue(1337);
    }

    @Test
    public void easyBoardIsRightSize() {
        assertTrue(game.getGameState().length == 9);
        assertTrue(game.getGameState()[0].length == 9);
    }

    @Test
    public void intermediateBoardIsRightSize() {
        game = new Game(1);
        assertTrue(game.getGameState().length == 16);
        assertTrue(game.getGameState()[0].length == 16);
    }

    @Test
    public void hardBoardIsRightSize() {
        game = new Game(2);
        assertTrue(game.getGameState().length == 16);
        assertTrue(game.getGameState()[0].length == 30);
    }

    @Test
    public void customdBoardIsRightSize() {
        game = new Game(11, 12, 13);
        assertTrue(game.getGameState().length == 12);
        assertTrue(game.getGameState()[0].length == 11);
    }

    @Test
    public void customBoardSetsMaxMinesCorrectly() {
        game = new Game(12, 13, 199);
        game.openCell(5, 5);
        int c = 0;
        char[][] board = game.getGameState();
        for (char[] row : board) {
            for(char cell : row) {
                if (cell == 'F') {
                    c++;
                }
            }
        }
        assertEquals(155, c);
    }

    @Test
    public void openCellOpensCell() {
        game.openCell(0, 8);
        assertTrue(game.getGameState()[8][0] == ' ');
    }

    @Test
    public void openCellOpensMultipleCells() {
        game.openCell(0, 8);
        int c = 0;
        char[][] board = game.getGameState();
        for (char[] row : board) {
            for(char cell : row) {
                if (cell == '#') {
                    c++;
                }
            }
        }
        assertEquals(72, c);
    }

    @Test
    public void flagCellWorks() {
        game.flagCell(5, 5);
        assertTrue(game.getGameState()[5][5] == 'F');
        game.flagCell(5, 5);
        assertTrue(game.getGameState()[5][5] == '#');
    }

    @Test
    public void openCellCantBeFlagged() {
        game.openCell(5, 5);
        game.flagCell(5, 5);
        assertFalse(game.getGameState()[5][5] == 'F');
    }

    @Test
    public void flaggingBlocksRecursiveOpening() {
        game.flagCell(4, 2);
        game.openCell(2, 2);
        int c = 0;
        char[][] board = game.getGameState();
        for (char[] row : board) {
            for(char cell : row) {
                if (cell == '#') {
                    c++;
                }
            }
        }
        assertEquals(59, c);
    }

    @Test
    public void openCellstartsGameClock() {
        game.openCell(6, 7);
        assertTrue(game.getStartTime() != 0);
    }

    @Test
    public void flagCelltStartsGameClock() {
        game.flagCell(6, 7);
        assertTrue(game.getStartTime() != 0);
    }

    @Test
    public void hittingMineStopsGameClock() {
        game.openCell(0, 0);
        game.openCell(0, 1);
        assertTrue(game.getEndTime() != 0);
    }

    @Test
    public void openingAllEmptyCellsSetsWinState() {
        game.openCell(0, 0);
        game.openCell(1, 0);
        game.openCell(2, 0);
        game.openCell(2, 2);
        game.openCell(0, 5);
        game.openCell(5, 5);
        game.openCell(3, 6);
        game.openCell(5, 6);
        game.openCell(0, 7);
        game.openCell(3, 8);
        game.openCell(4, 8);
        assertTrue(game.playerWins());
    }

    @Test
    public void winningStopsGameClock() {
        game.openCell(0, 0);
        game.openCell(1, 0);
        game.openCell(2, 0);
        game.openCell(2, 2);
        game.openCell(0, 5);
        game.openCell(5, 5);
        game.openCell(3, 6);
        game.openCell(5, 6);
        game.openCell(0, 7);
        game.openCell(3, 8);
        game.openCell(4, 8);
        assertTrue(game.getEndTime() != 0);
    }
    
}
