package minesweeper.engine;

public class Game {

    Board board;
    private boolean firstMoveDone;
    private boolean playerWins;

    public Game(int difficulty) {
        int[] width = new int[] {9, 16, 30};
        int[] height = new int[] {9, 16, 16};
        int[] mines = new int[] {10, 40, 99};
        this.board = new Board(width[difficulty], height[difficulty], mines[difficulty]);
    }

    public Cell[][] getBoard() {
        return this.board.getBoard();
    }

	public boolean nextMove(int x, int y) {
        if (!firstMoveDone) {
            this.board.generateMinefield(x, y);
            this.firstMoveDone = true;
        }
        this.board.openCell(x, y);
        if (board.getGameOver()) {
            return false;
        }
        if (board.allCellsOpen()) {
            this.playerWins = true;
            return false;
        }
        return true;
    }

    public boolean playerWins() {
        return this.playerWins;
    }
    
    public void useSeedValue(int seed) {
        this.board.setSeed(seed);
    }
}
