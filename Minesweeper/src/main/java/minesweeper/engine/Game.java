package minesweeper.engine;

/**
 * Class for the game logic.
 */

public class Game {

    Board board;
    private char[][] gameState;
    private boolean firstMoveDone;
    private boolean playerWins;
    private long startTime;
    private long endTime;

    /**
     * Sets up a new game.
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard
     */

    public Game(int difficulty) {
        int[] width = new int[] { 9, 16, 30 };
        int[] height = new int[] { 9, 16, 16 };
        int[] mines = new int[] { 10, 40, 99 };
        this.board = new Board(width[difficulty], height[difficulty], mines[difficulty]);
        this.gameState = board.getBoard();
    }

    /**
     * Opens a cell, updates the game state and checks if end conditions are met.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return Returns true if game continues.
     */

    public boolean openCell(int x, int y) {
        checkIfFirstMove(x, y);
        this.board.openCell(x, y);
        updateGameState();
        return (!checkIfMineHit(x, y) && !checkIfPlayerWins());
    }

    /**
     * Toggles flag.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */

    public void flagCell(int x, int y) {
        this.board.flagCell(x, y);
        updateGameState();
        startTimer();
    }

    private boolean checkIfMineHit(int x, int y) {
        if (board.getMineHit()) {
            this.endTime = System.currentTimeMillis();
            this.board.openMines();
            updateGameState();
            this.gameState[y][x] = 'X';
            return true;
        }
        return false;
    }

    private boolean checkIfPlayerWins() {
        if (board.allCellsOpen()) {
            this.endTime = System.currentTimeMillis();
            this.board.flagMines();
            updateGameState();
            this.playerWins = true;
            return true;
        }
        return false;
    }

    private void checkIfFirstMove(int x, int y) {
        if (!firstMoveDone) {
            this.board.generateMinefield(x, y);
            this.firstMoveDone = true;
            startTimer();
        }
    }

    private void startTimer() {
        if (this.startTime == 0) {
            this.startTime = System.currentTimeMillis();
        }
    }

    /**
     * Returns win status.
     * @return true if player has won.
     */

    public boolean playerWins() {
        return this.playerWins;
    }

    /**
     * Sets seed value.
     * @param seed Value to be used.
     */

    public void useSeedValue(int seed) {
        this.board.setSeed(seed);
    }

    private void updateGameState() {
        this.gameState = board.getBoard();
    }

    /**
     * Returns a char array representation of the game state.
     * @return game state representation.
     */

    public char[][] getGameState() {
        return this.gameState;
    }
}
