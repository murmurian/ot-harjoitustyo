package minesweeper.engine;

import java.io.File;
import java.util.List;
import minesweeper.dao.FileHighscoresDao;

/**
 * Class for the game logic.
 */
public class Game {

    private Board board;
    private char[][] gameState;
    private boolean firstMoveDone;
    private boolean playerWins;
    private long startTime;
    private long endTime;
    private int difficulty;

    /**
     * Sets up a new game.
     *
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard
     */
    public Game(int difficulty) {
        this.difficulty = difficulty;
        int[] width = new int[] { 9, 16, 30 };
        int[] height = new int[] { 9, 16, 16 };
        int[] mines = new int[] { 10, 40, 99 };
        this.board = new Board(width[difficulty], height[difficulty], mines[difficulty]);
        this.gameState = board.getBoard();
    }

    /**
     * Sets up a new game for custom difficulty.
     *
     * @param width  Custom width.
     * @param height Custom height.
     * @param mines  Custom number of mines.
     */
    public Game(int width, int height, int mines) {
        this.difficulty = 3;
        if (mines >= width * height) {
            this.board = new Board(width, height, width * height - 1);
        } else {
            this.board = new Board(width, height, mines);
        }
        this.gameState = board.getBoard();
    }

    /**
     * Opens a cell, updates the game state and checks if end conditions are met.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void openCell(int x, int y) {
        checkIfFirstMove(x, y);
        this.board.openCell(x, y);
        updateGameState();
        checkIfMineHit(x, y);
        checkIfPlayerWins();
    }

    /**
     * Toggles flag.
     *
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
     *
     * @return true if player has won.
     */
    public boolean playerWins() {
        return this.playerWins;
    }

    /**
     * Sets seed value.
     *
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
     *
     * @return game state representation.
     */
    public char[][] getGameState() {
        return this.gameState;
    }

    /**
     * Returns system time from first opened cell.
     *
     * @return start time.
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Returns system time of the moment player has won or lost.
     *
     * @return end time.
     */
    public long getEndTime() {
        return this.endTime;
    }

    /**
     * Checks if player has made a top 10 high score.
     *
     * @return boolean value.
     * @throws Exception in case of a file error.
     */
    public boolean isHighScore() throws Exception {
        File fileName = new File("scores.txt");
        FileHighscoresDao scores = new FileHighscoresDao(fileName);
        return scores.checkIfHighscore(difficulty, (int) ((this.endTime - this.startTime) / 1000));
    }

    /**
     * Adds players score to high scores.
     * 
     * @param name players name.
     * @throws Exception in case of a file error.
     */
    public void setHighscore(String name) throws Exception {
        File fileName = new File("scores.txt");
        FileHighscoresDao scores = new FileHighscoresDao(fileName);
        scores.addScore(this.difficulty, (int) ((this.endTime - this.startTime) / 1000), name);
    }

    /**
     * Returns top 10 list of best times for given difficulty as a String.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @return list as String.
     * @throws Exception in case of a file error.
     */
    public String getHighscores(int difficulty) throws Exception {
        File fileName = new File("scores.txt");
        FileHighscoresDao scores = new FileHighscoresDao(fileName);
        List<String> list = scores.getHighscores(difficulty);
        String highscores = "";
        for (int i = 0; i < list.size(); i++) {
            highscores += i + 1 + ". " + list.get(i) + "\n";
        }

        return highscores;
    }

    public int getDifficulty() {
        return this.difficulty;
    }
}
