package minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Board board;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter difficulty: 1(easy), 2(intermediate), 3(hard) ?");
        String difficulty = scanner.nextLine();

        switch (difficulty) {
            case "1":
                board = new Board(9, 9, 10);
                break;
            case "2":
                board = new Board(16, 16, 40);
                break;
            case "3":
                board = new Board(30, 16, 99);
                break;
            default:
                board = new Board(9, 9, 10);
        }

        System.out.println(Arrays.deepToString(board.getBoard()).replace("], ", "]\n "));

    }
}
