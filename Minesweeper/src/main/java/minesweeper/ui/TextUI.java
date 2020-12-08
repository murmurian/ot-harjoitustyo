package minesweeper.ui;

import minesweeper.engine.*;
import java.util.Arrays;
import java.util.Scanner;

public class TextUI {
    private Game game;
    private Scanner scanner = new Scanner(System.in);

    public void newGame() {
        System.out.println("Welcome to Minesweeper! TextUI used for development. Beware, there are no validation for inputs.");
        System.out.println("Enter difficulty: 1 (easy), 2 (intermediate), 3 (hard) ?");
        String difficulty = scanner.nextLine();
        switch (difficulty) {
            case "1":
                game = new Game(0);
                break;
            case "2":
                game = new Game(1);
                break;
            case "3":
                game = new Game(2);
                break;
            default:
                game = new Game(0);
        }

        // To play a constant board, set a seed value:
        game.useSeedValue(1337);
        
        printBoard();
        startGame();
    }

    private void startGame() {
        while (true) {
            System.out.println("Enter x-coordinate (0 - width-1, q to quit): ");
            String x = scanner.nextLine();
            if (x.equals("q")) {
                break;
            }
            System.out.println("Enter y-coordinate (0 - height-1, q to quit): ");
            String y = scanner.nextLine();
            if (y.equals("q")) {
                break;
            }
            if (!game.openCell(Integer.valueOf(x), Integer.valueOf(y))) {
                printBoard();
                if (game.playerWins()) {
                    System.out.println("You won!");
                } else {
                    System.out.println("You lost!");
                }                
                break;
            }

            printBoard();

        }
    }

    private void printBoard() {
        System.out.println(Arrays.deepToString(game.getGameState()).replace("], ", "]\n "));
    }

}
