package minesweeper.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class has methods for creating empty board and setting the mines with or
 * without a seed value.
 */

public class Generator {
    private Cell[][] board;
    private int seed; // Seed value used mainly for generating constant board for testing. It could be
                      // utilized for saving particular board setup for replays.

    /**
     * Basic genator object is created with random seed value.
     */

    public Generator() {
        this.seed = generateSeed();
    }

    /**
     * Generates an empty board.
     */

    public Cell[][] generateBoard(int width, int height) {
        Cell[][] board = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
        return board;
    }

    private int generateSeed() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * Method places mines on the empty board when first cell is opened. The first
     * opened cell is never a mine.
     * 
     * @param firstX     x-coordinate of the first opened cell
     * @param firstY     y-coordinate of the first opened cell
     * @param emptyBoard method requires an empty board
     * @param mines      number of mines to be set
     */

    public void generateMinefield(int firstX, int firstY, Cell[][] emptyBoard, int mines) {
        this.board = emptyBoard;
        ArrayList<Integer> list = new ArrayList<>();
        int x, y = 0;
        int mineCount = mines;
        for (int i = 0; i < this.board.length * this.board[0].length; i++) {
            list.add(i);
        }
        Collections.shuffle(list, new Random(this.seed));
        for (int i = 0; i < mineCount; i++) {
            y = list.get(i) / this.board.length;
            x = list.get(i) % this.board[0].length;
            if (x == firstX && y == firstY) {
                mineCount++;
                continue;
            }
            this.board[y][x].setIsMine();
            setMinesNear(x, y);
        }
    }

    private void setMinesNear(int x, int y) {
        setMinesUp(x, y);
        setMinesHorizontal(x, y);
        setMinesDown(x, y);
    }

    private void setMinesUp(int x, int y) {
        if (y > 0) {
            this.board[y - 1][x].addMinesNear();
            if (x > 0) {
                this.board[y - 1][x - 1].addMinesNear();
            }
            if (x < this.board.length - 1) {
                this.board[y - 1][x + 1].addMinesNear();
            }
        }
    }

    private void setMinesHorizontal(int x, int y) {
        if (x > 0) {
            this.board[y][x - 1].addMinesNear();
        }
        if (x < this.board.length - 1) {
            this.board[y][x + 1].addMinesNear();
        }
    }

    private void setMinesDown(int x, int y) {
        if (y < this.board[0].length - 1) {
            this.board[y + 1][x].addMinesNear();
            if (x > 0) {
                this.board[y + 1][x - 1].addMinesNear();
            }
            if (x < this.board.length - 1) {
                this.board[y + 1][x + 1].addMinesNear();
            }
        }
    }

    /**
     * Sets a custom seed value. Can be used for generating consist board for
     * testing or saving a board for replay.
     */

    public void setSeed(int seed) {
        this.seed = seed;
    }

    /**
     * Returns board. Use after mines have been generated.
     */

    public Cell[][] getBoard() {
        return this.board;
    }
}
