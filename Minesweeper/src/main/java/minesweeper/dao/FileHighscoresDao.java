package minesweeper.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class FileHighscoresDao implements HighscoresDao {

    private TreeMap<Integer, String>[] highscores;
    private File fileName;

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

    @Override
    public List<String> getHighscores(int difficulty) {
        ArrayList<String> scoreList = new ArrayList<>();
        highscores[difficulty].entrySet().forEach((e) -> {
            scoreList.add(e.getValue() + " " + e.getKey() + "s");
        });
        return scoreList;
    }

    @Override
    public boolean checkIfHighscore(int difficulty, int time) {
        if (highscores[difficulty].size() < 10) {
            return true;
        }
        return highscores[difficulty].lastEntry().getKey() > time;
    }

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
