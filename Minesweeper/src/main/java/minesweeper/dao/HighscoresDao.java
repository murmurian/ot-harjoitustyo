package minesweeper.dao;

import java.util.List;

public interface HighscoresDao {
    List<String> getHighscores(int difficulty);
    boolean checkIfHighscore(int difficulty, int time);
    void addScore(int difficulty, int time, String name);
}
