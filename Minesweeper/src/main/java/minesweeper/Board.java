package minesweeper;

public class Board {
    private Cell[][] board;

    public Board(int width, int height, int mines) {
        this.board = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = new Cell();
            }
        }
    }

    public Cell[][] getBoard() {
        return this.board;
    }
    
}
