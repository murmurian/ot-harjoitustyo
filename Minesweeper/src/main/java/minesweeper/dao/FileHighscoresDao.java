package minesweeper.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileHighscoresDao implements HighscoresDao {
    private TreeMap<Integer, String> easy;
    private TreeMap<Integer, String> intermediate;
    private TreeMap<Integer, String> hard;
    private TreeMap<Integer, String> custom;
    private String file;

    public FileHighscoresDao(String file) throws Exception {
        easy = new TreeMap<>();
        intermediate = new TreeMap<>();
        hard = new TreeMap<>();
        custom = new TreeMap<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int difficulty = Integer.parseInt(parts[0]);
                int time = Integer.parseInt(parts[1]);
                String name = parts[2];
                switch (difficulty) {
                    case 0:
                        easy.put(time, name);
                        break;
                    case 1:
                        intermediate.put(time, name);
                        break;
                    case 2:
                        hard.put(time, name);
                        break;
                    default:
                        custom.put(time, name);
                }
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    @Override
    public List<String> getHighscores(int difficulty) {
        ArrayList<String> highscores = new ArrayList<>();
        switch (difficulty) {
            case 0:
                for (Map.Entry<Integer, String> e : easy.entrySet()) {
                    highscores.add(e.getKey() + " " + e.getValue());
                }
                break;
            case 1:
                for (Map.Entry<Integer, String> e : intermediate.entrySet()) {
                    highscores.add(e.getKey() + " " + e.getValue());
                }
                break;
            case 2:
                for (Map.Entry<Integer, String> e : hard.entrySet()) {
                    highscores.add(e.getKey() + " " + e.getValue());
                }
                break;
            default:
                for (Map.Entry<Integer, String> e : custom.entrySet()) {
                    highscores.add(e.getKey() + " " + e.getValue());
                }
                break;
        }
        return highscores;
    }

    @Override
    public boolean checkIfHighscore(int difficulty, int time) {
        switch (difficulty) {
            case 0:
                return easy.lastEntry().getKey() > time;
            case 1:
                return intermediate.lastEntry().getKey() > time;
            case 2:
                return hard.lastEntry().getKey() > time;
            default:
                return custom.lastEntry().getKey() > time;
        }
    }

    @Override
    public void addScore(int difficulty, int time, String name) throws Exception {
        switch (difficulty) {
            case 0:
                easy.put(time, name);
                if (easy.size() > 10) {
                    easy.pollLastEntry();
                }
                break;
            case 1:
                intermediate.put(time, name);
                if (intermediate.size() > 10) {
                    intermediate.pollLastEntry();
                }
                break;
            case 2:
                hard.put(time, name);
                if (hard.size() > 10) {
                    hard.pollLastEntry();
                }
                break;
            default:
                custom.put(time, name);
                if (hard.size() > 10) {
                    hard.pollLastEntry();
                }
        }
        save();
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Map.Entry<Integer, String> e : easy.entrySet()) {
                writer.write("0" + ";" + e.getKey() + ";" + e.getValue() + "\n");
            }
            for (Map.Entry<Integer, String> e : intermediate.entrySet()) {
                writer.write("1" + ";" + e.getKey() + ";" + e.getValue() + "\n");
            }
            for (Map.Entry<Integer, String> e : hard.entrySet()) {
                writer.write("2" + ";" + e.getKey() + ";" + e.getValue() + "\n");
            }
            for (Map.Entry<Integer, String> e : custom.entrySet()) {
                writer.write("3" + ";" + e.getKey() + ";" + e.getValue() + "\n");
            }
        }
    }

}
