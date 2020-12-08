package minesweeper.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generator {
    private Cell[][] board;
    private int seed; // Seed value used mainly for generating constant board for testing. It could be
                      // utilized for saving particular board setup for replays.

    public Generator() {
        this.seed = generateSeed();
    }

    public Cell[][] generateBoard(int width, int height) {
        Cell[][] board = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
        return board;
    }

    public int generateSeed() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }

    public void generateMinefield(int firstX, int firstY, Cell[][] emptyBoard, int mines) {
        this.board = emptyBoard;
        ArrayList<Integer> list = new ArrayList<>();
        int x, y = 0;
        int mineCount = mines;
        for (int i = 0; i <  this.board.length * this.board[0].length; i++) {
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

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
