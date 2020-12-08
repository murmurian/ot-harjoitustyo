package minesweeper.ui;

import minesweeper.engine.Game;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MinesweeperGUI extends Application {

    private GridPane board;
    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(0);
        game.useSeedValue(1337);
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
                
                if (game.getGameState()[y][x] != '#') {
                    board.add(cell, x, y);
                    cell.setDisable(true);
                    cell.setStyle("-fx-text-fill: #000000");
                    //cell.setStyle("-fx-background-color: #ff00ff");                    
                }
                if (game.getGameState()[y][x] == '#') {
                    board.add(cell, x, y);
                    cell.setText(" ");
                }
                int rx = x;
                int ry = y;
                cell.setOnAction((event) -> {
                    game.openCell(rx, ry);
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
