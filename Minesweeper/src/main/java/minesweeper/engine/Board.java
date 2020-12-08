package minesweeper.engine;

/**
 * Class controls the creation and the state of the board.
 */

public class Board {
    private Cell[][] board;
    private final int width;
    private final int height;
    private int mines;
    private int cellsNotOpen;
    private boolean mineHit;
    private Generator generator;

    /**
     * Constructor creates an empty board with specified values.
     */

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.cellsNotOpen = width * height - mines;
        this.generator = new Generator();
        this.board = generator.generateBoard(width, height);
    }

    /**
     * Method places mines on the empty board when first cell is opened.
     * The first opened cell is never a mine.
     * @param firstX x-coordinate of the first opened cell
     * @param firstY y-coordinate of the first opened cell
     */

    public void generateMinefield(int firstX, int firstY) {
        generator.generateMinefield(firstX, firstY, this.board, mines);
        this.board = generator.getBoard();
    }

    /**
     * Opens cell on the board if it is not open or flagged.
     * Recurcively opens adjacent cells, if the cell has zero mines near.
     */

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

    /**
     * Places a flag on the cell or removes it.
     */

    public void flagCell(int x, int y) {
        if (!this.board[y][x].getIsOpen() && !this.board[y][x].getIsFlagged()) {
            this.board[y][x].setIsFlagged();
            System.out.println(this.board[y][x].getIsFlagged());
        } else if (!this.board[y][x].getIsOpen()) {
            this.board[y][x].setIsNotFlagged();
        }
    }

    /**
     * Returns a char array representation of the game state.
     */

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

    /**
     * Opens all the cells with mines.
     * Call this method when player hits mine.
     */

    public void openMines() {
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                if (cell.getIsMine()) {
                    cell.setIsOpen();
                }
            }
        }
    }

    /**
     * Flags all the unopened cells.
     * Call this method when all the cells without mines are open.
     */

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

    /**
     * Return true when all cells without mine have been opened.
     */

    public boolean allCellsOpen() {
        return cellsNotOpen == 0;
    }

    public boolean getMineHit() {
        return this.mineHit;
    }

    public void setSeed(int seed) {
        generator.setSeed(seed);
    }

}
