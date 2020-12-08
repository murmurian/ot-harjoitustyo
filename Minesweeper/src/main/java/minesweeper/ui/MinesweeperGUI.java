package minesweeper.ui;

import minesweeper.engine.Game;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Graphic user interface.
 */

public class MinesweeperGUI extends Application {

    private GridPane board;
    private Game game;

    /**
     * Creates the layout.
     */

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(0);
        // game.useSeedValue(1337);
        Font width = Font.font("Monospaced", 20);
        Label label = new Label("Minesweeper");
        label.setFont(width);
        BorderPane layout = new BorderPane();
        layout.setTop(label);
        board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        drawButtons();
        layout.setCenter(board);
        Scene view = new Scene(layout);
        stage.setScene(view);
        stage.show();
    }

    private void drawButtons() {
        Font width = Font.font("Monospaced", 15);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button cell = new Button(String.valueOf(game.getGameState()[y][x]));
                cell.setFont(width);
                board.add(cell, x, y);
                if (game.getGameState()[y][x] != '#' && game.getGameState()[y][x] != 'F') {
                    cell.setDisable(true);
                    cell.setStyle("-fx-text-fill: #000000");
                    // cell.setStyle("-fx-background-color: #ff00ff");
                } else if (game.getGameState()[y][x] == '#') {
                    cell.setText(" ");
                } else {
                    cell.setText("F");
                }
                int rx = x;
                int ry = y;
                cell.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        game.openCell(rx, ry);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        game.flagCell(rx, ry);
                    }
                    board.getChildren().clear();
                    drawButtons();
                });
            }
        }
    }

    public static void main(String[] args) {
        launch(MinesweeperGUI.class);
    }

}
