package minesweeper;


import minesweeper.engine.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Test
    public void easyBoardIsRightSize() {
        game = new Game(0);
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
    
}
