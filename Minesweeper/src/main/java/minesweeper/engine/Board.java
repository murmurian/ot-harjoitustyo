package minesweeper.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {
    private Cell[][] board;
    private final int width;
    private final int height;
    private int mines;
    private int cellsNotOpen;
    private int seed; // Seed value used mainly for generating constant board for testing. It could be
                      // utilized for saving particular board setup for replays.
    private boolean mineHit;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.cellsNotOpen = width * height - mines;
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
            if (x < this.width - 1) {
                this.board[y - 1][x + 1].addMinesNear();
            }
        }
    }

    private void setMinesHorizontal(int x, int y) {
        if (x > 0) {
            this.board[y][x - 1].addMinesNear();
        }
        if (x < this.width - 1) {
            this.board[y][x + 1].addMinesNear();
        }
    }

    private void setMinesDown(int x, int y) {
        if (y < this.height - 1) {
            this.board[y + 1][x].addMinesNear();
            if (x > 0) {
                this.board[y + 1][x - 1].addMinesNear();
            }
            if (x < this.width - 1) {
                this.board[y + 1][x + 1].addMinesNear();
            }
        }
    }

    public void openCell(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return;
        }
        if (this.board[y][x].getIsOpen() || this.board[y][x].getIsFlagged()) {
            return;
        }              
        if (this.board[y][x].getIsMine()) {
            this.mineHit = true;
            return;
        }
        this.board[y][x].setIsOpen();  
        if (this.board[y][x].getMinesNear() == 0) {
            openCellsNear(x, y);
        }
        this.cellsNotOpen--;
    }

    private void openCellsNear(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                openCell(x + i, y + j);
            }
        }
    }

    public void flagCell(int x, int y) {
        if (!this.board[y][x].getIsOpen() && !this.board[y][x].getIsFlagged()) {
            this.board[y][x].setIsFlagged();
            System.out.println(this.board[y][x].getIsFlagged());
        } else if (!this.board[y][x].getIsOpen()) {
            this.board[y][x].setIsNotFlagged();
        }
    }

    public char[][] getBoard() {
        char[][] gameBoard = new char[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (this.board[y][x].getIsFlagged()) {
                    gameBoard[y][x] = 'F';
                } else if (!this.board[y][x].getIsOpen()) {
                    gameBoard[y][x] = '#';
                } else if (this.board[y][x].getIsMine()) {
                    gameBoard[y][x] = '*';
                } else if (this.board[y][x].getMinesNear() == 0) {
                    gameBoard[y][x] = ' ';
                } else {
                    gameBoard[y][x] = (char) (this.board[y][x].getMinesNear() + '0');
                }
            }
        }
        return gameBoard;
    }

    public void openMines() {
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                if (cell.getIsMine()) {
                    cell.setIsOpen();
                }
            }
        }
    }

    public void flagMines() {
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                if (cell.getIsMine()) {
                    cell.setIsFlagged();
                }
            }
        }
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

    public boolean allCellsOpen() {
        return cellsNotOpen == 0;
    }

    public int getSeed() {
        return this.seed;
    }

    public boolean getMineHit() {
        return this.mineHit;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

}
