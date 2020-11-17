package minesweeper;

public class Board {
    private Cell[][] board;
    private final int width;
    private final int height;
    private final int mines;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.board = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
    }

    public void openCell(int x, int y) {
        this.board[y][x].setIsOpen();
    }

    public Cell[][] getBoard() {
        return this.board;
    }
    
}
