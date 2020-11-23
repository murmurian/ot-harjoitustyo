package minesweeper.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {
    private Cell[][] board;
    private final int width;
    private final int height;
    private int mines;
    private int seed; // Seed value used mainly for generating constant board for testing. It could be
                      // utilized for saving particular board setup for replays.

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.board = generateBoard();
        this.seed = generateSeed();
    }

    public Cell[][] generateBoard() {
        Cell[][] board = new Cell[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                board[y][x] = new Cell();
            }
        }
        return board;
    }

    public int generateSeed() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }

    public void generateMinefield(int firstX, int firstY) {
        ArrayList<Integer> list = new ArrayList<>();
        int x, y = 0;
        for (int i = 0; i < this.width * this.height; i++) {
            list.add(i);
        }
        Collections.shuffle(list, new Random(this.seed));
        for (int i = 0; i < mines; i++) {
            y = list.get(i) / this.width;
            x = list.get(i) % this.width;
            if (x == firstX && y == firstY) {
                mines++;
                continue;
            }
            this.board[y][x].setIsMine();
            setMinesNear(x, y);
        }
    }

    public void setMinesNear(int x, int y) {
        if (y > 0) {
            this.board[y - 1][x].addMinesNear();
            if (x > 0) {
                this.board[y - 1][x - 1].addMinesNear();
            }
            if (x < this.width - 1) {
                this.board[y - 1][x + 1].addMinesNear();
            }
        }
        if (y < this.height - 1) {
            this.board[y + 1][x].addMinesNear();
            if (x > 0) {
                this.board[y + 1][x - 1].addMinesNear();
            }
            if (x < this.width - 1) {
                this.board[y + 1][x + 1].addMinesNear();
            }
        }
        if (x > 0) {
            this.board[y][x - 1].addMinesNear();
        }
        if (x < this.width - 1) {
            this.board[y][x + 1].addMinesNear();
        }
    }

    public void openCell(int x, int y) {
        this.board[y][x].setIsOpen();
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public Cell getCell(int x, int y) {
        return this.board[y][x];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMines() {
        return this.mines;
    }

    public int getSeed() {
        return this.seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

}
