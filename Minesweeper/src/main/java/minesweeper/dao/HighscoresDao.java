package minesweeper.dao;

import java.util.List;

/**
 * Interface for high scores management.
 */

public interface HighscoresDao {
    /**
     * Method returns top 10 list of high scores for given difficulty.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @return List<String>
     */
    List<String> getHighscores(int difficulty);

    /**
     * Method checks if given time is top 10 in given difficulty level.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @param time       time (int) in seconds.
     * @return boolean true if top 10.
     */
    boolean checkIfHighscore(int difficulty, int time);

    /**
     * Method adds a high score to top 10 list.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @param time       time (int) in seconds.
     * @param name       players name.
     */
    void addScore(int difficulty, int time, String name);
}
