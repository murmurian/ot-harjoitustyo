package minesweeper.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHighscoresDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();   

    File file;
    FileHighscoresDao highscore;

    @Before
    public void setUp() throws Exception {
        file = new File("test.txt");
        try {
            file = File.createTempFile("tmp", null);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        highscore = new FileHighscoresDao(file);

    }

    @Test
    public void easyHighscoreIsSaved() {
        highscore.addScore(0, 123, "test1");
        assertTrue(highscore.getHighscores(0).get(0).equals("test1 123s"));
    }

    @Test
    public void intermediateHighscoreIsSaved() {
        highscore.addScore(1, 223, "test2");
        assertTrue(highscore.getHighscores(1).get(0).equals("test2 223s"));
    }

    @Test
    public void hardHighscoreIsSaved() {
        highscore.addScore(2, 423, "test3");
        assertTrue(highscore.getHighscores(2).get(0).equals("test3 423s"));
    }

    @Test
    public void customHighscoreIsSaved() {
        highscore.addScore(3, 123, "test4");
        assertTrue(highscore.getHighscores(3).get(0).equals("test4 123s"));
    }

    @Test
    public void highscoreSortingWorks() {
        highscore.addScore(1, 223, "test1");
        highscore.addScore(1, 123, "test2");
        highscore.addScore(1, 423, "test3");
        assertTrue(highscore.getHighscores(1).get(0).equals("test2 123s"));
        assertTrue(highscore.getHighscores(1).get(1).equals("test1 223s"));
        assertTrue(highscore.getHighscores(1).get(2).equals("test3 423s"));
    }

    @Test
    public void highscoreCheckWorks() {
        for (int i = 100; i < 111; i++) {
            highscore.addScore(1, i, String.valueOf(i));
        }
        assertTrue(highscore.getHighscores(1).size() == 10);
        assertTrue(highscore.checkIfHighscore(1, 99));
        assertFalse(highscore.checkIfHighscore(1, 112));
        highscore.addScore(1, 99, "test1");
        assertTrue(highscore.getHighscores(1).size() == 10);
        assertTrue(highscore.getHighscores(1).get(0).equals("test1 99s"));
    }

    @Test
    public void highscoreMaxTop10() {
        for (int i = 100; i < 111; i++) {
            highscore.addScore(1, i, String.valueOf(i));
        }
        highscore.addScore(1, 99, "test1");
        assertTrue(highscore.getHighscores(1).size() == 10);
        assertTrue(highscore.getHighscores(1).get(0).equals("test1 99s"));
        assertTrue(highscore.getHighscores(1).get(9).equals("108 108s"));
        highscore.addScore(1, 200, "test2");
        assertTrue(highscore.getHighscores(1).get(9).equals("108 108s"));
    }

    @After
    public void tearDown() {
        file.delete();
    }
    
}
