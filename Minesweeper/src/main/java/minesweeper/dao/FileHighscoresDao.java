package minesweeper.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class manages high scores for all difficulties. Top 10 high scores are saved
 * to file. Class uses TreeMap-structure to sort scores.
 */

public class FileHighscoresDao implements HighscoresDao {

    private TreeMap<Integer, String>[] highscores;
    private File fileName;

    /**
     * Reads high scores from given file and creates a new file if necessary.
     * 
     * @param fileName High scores file.
     * @throws Exception filesystem error.
     */

    public FileHighscoresDao(File fileName) throws Exception {
        highscores = new TreeMap[4];
        for (int i = 0; i <= 3; i++) {
            highscores[i] = new TreeMap<>();
        }
        this.fileName = fileName;
        try {
            Scanner reader = new Scanner(fileName);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int difficulty = Integer.parseInt(parts[0]);
                int time = Integer.parseInt(parts[1]);
                String name = parts[2];
                highscores[difficulty].put(time, name);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(fileName);
            writer.close();
        }
    }

    /**
     * Returns list of 10 best times for given difficulty.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @return ArrayList of names and times.
     */

    @Override
    public List<String> getHighscores(int difficulty) {
        ArrayList<String> scoreList = new ArrayList<>();
        highscores[difficulty].entrySet().forEach((e) -> {
            scoreList.add(e.getValue() + " " + e.getKey() + "s");
        });
        return scoreList;
    }

    /**
     * Checks if players time is high score for given difficulty.
     * 
     * @param difficulty 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @param time       players time.
     * @return returns true if time is top 10.
     */

    @Override
    public boolean checkIfHighscore(int difficulty, int time) {
        if (highscores[difficulty].size() < 10) {
            return true;
        }
        return highscores[difficulty].lastEntry().getKey() > time;
    }

    /**
     * Adds high score to top 10 list.
     * 
     * @param difficulty int 0 = easy, 1 = intermediate, 2 = hard, 3 = custom.
     * @param time       int players time.
     * @param name       String players name.
     * @return returns true if time is top 10.
     */

    @Override
    public void addScore(int difficulty, int time, String name) {
        highscores[difficulty].put(time, name);
        if (highscores[difficulty].size() > 10) {
            highscores[difficulty].pollLastEntry();
        }
        try {
            save();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i <= 3; i++) {
                for (var e : highscores[i].entrySet()) {
                    writer.write(i + ";" + e.getKey() + ";" + e.getValue() + "\n");
                }
            }
        }
    }
}
