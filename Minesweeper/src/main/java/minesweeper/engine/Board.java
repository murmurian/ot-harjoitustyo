package minesweeper.engine;

public class Board {
    private Cell[][] board;
    private final int width;
    private final int height;
    private int mines;
    private int cellsNotOpen;
    private boolean mineHit;
    private Generator generator;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.cellsNotOpen = width * height - mines;
        this.generator = new Generator();
        this.board = generator.generateBoard(width, height);
    }

    public void generateMinefield(int firstX, int firstY) {
        generator.generateMinefield(firstX, firstY, this.board, mines);
        this.board = generator.getBoard();
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

    public boolean getMineHit() {
        return this.mineHit;
    }

    public void setSeed(int seed) {
        generator.setSeed(seed);
    }

}
