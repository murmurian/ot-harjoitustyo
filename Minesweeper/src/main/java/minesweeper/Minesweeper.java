package minesweeper;

import minesweeper.ui.*;

public class Minesweeper {

    /**
     * Launches user interface.
     * @param args main method does not take arguments.
     */
    public static void main(String[] args) {
        //MinesweeperGUI.main(args);
        TextUI textUI = new TextUI();
        textUI.newGame();
    }
}
