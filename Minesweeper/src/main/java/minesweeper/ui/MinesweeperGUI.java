package minesweeper.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import minesweeper.engine.Game;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Graphic user interface.
 */
public class MinesweeperGUI extends Application {

    private Game game;
    private GridPane board;
    private BorderPane layout;
    private BorderPane topRow;
    private VBox leftColumn;
    private Font font;
    private Label clock;
    private Button newGame;
    private Slider width;
    private Slider height;
    private Slider mines;
    private int selectedDifficulty;
    private Image minePng;
    private Image flagPng;
    private Image deathPng;
    private Image flagHPng;

    @Override
    public void init() {
        game = new Game(0);
        selectedDifficulty = 0;
        layout = new BorderPane();
        topRow = new BorderPane();
        leftColumn = new VBox();
        board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        font = Font.font("Monospaced", 15);
        minePng = new Image("/mine.png");
        flagPng = new Image("/flag.png");
        deathPng = new Image("/death.png");
        flagHPng = new Image("/flagHover.png");
    }

    /**
     * Creates the layout.
     */
    @Override
    public void start(Stage stage) throws Exception {
        drawTopRow();
        drawLeftColumn();
        drawButtons();
        layout.setCenter(board);
        newGame.setOnAction((ActionEvent actionEvent) -> {
            clock.setText("0");
            if (selectedDifficulty == 3) {
                game = new Game((int) width.getValue(), (int) height.getValue(), (int) mines.getValue());
            } else {
                game = new Game(selectedDifficulty);
            }
            drawButtons();
            Platform.runLater(() -> {
                stage.sizeToScene();
            });
        });
        Scene view = new Scene(layout);
        stage.setScene(view);
        stage.show();
    }

    private void drawTopRow() {
        Label label = new Label("Minesweeper");
        label.setFont(font);
        label.setPadding(new Insets(10, 10, 10, 10));
        clock = new Label("0");
        clock.setFont(font);
        clock.setPadding(new Insets(10, 10, 10, 10));
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.getStartTime() != 0 && game.getEndTime() == 0) {
                    clock.setText(String.valueOf(System.currentTimeMillis() / 1000 - game.getStartTime() / 1000));
                }
            }
        }.start();
        topRow.setLeft(label);
        topRow.setRight(clock);
        layout.setTop(topRow);
    }

    private void drawLeftColumn() {
        newGame = new Button("New Game");
        newGame.setFocusTraversable(false);
        Button highscores = new Button("High scores");
        highscores.setFocusTraversable(false);
        highscores.setOnAction(actionEvent -> {
            try {
                showHighscores(selectedDifficulty);
            } catch (Exception ex) {
                Logger.getLogger(MinesweeperGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        RadioButton easy = new RadioButton("Easy");
        RadioButton intermediate = new RadioButton("Intermediate");
        RadioButton hard = new RadioButton("Hard");
        RadioButton custom = new RadioButton("Custom:");

        ToggleGroup radioGroup = new ToggleGroup();
        easy.setToggleGroup(radioGroup);
        intermediate.setToggleGroup(radioGroup);
        hard.setToggleGroup(radioGroup);
        custom.setToggleGroup(radioGroup);
        easy.setSelected(true);

        easy.setOnAction(event -> selectedDifficulty = 0);
        intermediate.setOnAction(event -> selectedDifficulty = 1);
        hard.setOnAction(event -> selectedDifficulty = 2);
        custom.setOnAction(event -> selectedDifficulty = 3);

        width = new Slider(9, 30, 9);
        height = new Slider(9, 20, 9);
        mines = new Slider(10, 200, 10);
        Label widthValue = new Label();
        widthValue.textProperty().bind(width.valueProperty().asString("%.0f"));
        Label heightValue = new Label();
        heightValue.textProperty().bind(height.valueProperty().asString("%.0f"));
        Label minesValue = new Label();
        minesValue.textProperty().bind(mines.valueProperty().asString("%.0f"));

        leftColumn.setPadding(new Insets(10, 10, 10, 10));
        leftColumn.setSpacing(10);
        leftColumn.getChildren().addAll(newGame, highscores, easy, intermediate, hard, custom);
        leftColumn.setSpacing(5);
        leftColumn.getChildren().addAll(new HBox(new Label("Width: "), widthValue), width,
                new HBox(new Label("Height: "), heightValue), height, new HBox(new Label("Mines: "), minesValue),
                mines);
        layout.setLeft(leftColumn);
    }

    private void drawButtons() {
        board.getChildren().clear();
        for (int y = 0; y < game.getGameState().length; y++) {
            for (int x = 0; x < game.getGameState()[0].length; x++) {
                Button cell = new Button(String.valueOf(game.getGameState()[y][x]));
                cell.setFont(font);
                board.add(cell, x, y);
                cell.setFocusTraversable(false);
                ImageView mineView = new ImageView(minePng);
                ImageView flagView = new ImageView(flagPng);
                ImageView flagHView = new ImageView(flagHPng);
                ImageView deathView = new ImageView(deathPng);
                cell.setMinSize(30, 30);
                cell.setMaxSize(30, 30);
                if (game.getGameState()[y][x] == 'X') {
                    cell.setText("");
                    cell.setGraphic(deathView);
                }
                if (game.getGameState()[y][x] == '*') {
                    cell.setText("");
                    cell.setGraphic(mineView);
                } else if (game.getGameState()[y][x] != '#' && game.getGameState()[y][x] != 'F') {
                    cell.setDisable(true);
                    cell.setOpacity(1);
                    cell.setStyle("-fx-background-color: #eeeeee");
                } else if (game.getGameState()[y][x] == '#') {
                    cell.setText(" ");
                } else {
                    cell.setText("");                    
                    if (game.getEndTime() == 0) {
                        cell.setGraphic(flagView);
                        cell.setOnMouseEntered(e -> cell.setGraphic(flagHView));
                        cell.setOnMouseExited(e -> cell.setGraphic(flagView));
                    } else {
                        cell.setGraphic(flagHView);
                    }
                }
                int rx = x;
                int ry = y;
                if (game.getEndTime() == 0) {
                    cell.setOnMouseClicked((event) -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            game.openCell(rx, ry);
                            if (game.playerWins()) {
                                try {
                                    checkIfHighScore();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            game.flagCell(rx, ry);
                        }
                        drawButtons();
                    });
                } else {
                    cell.setDisable(true);
                    cell.setOpacity(1);
                }
            }
        }
    }

    private void checkIfHighScore() throws Exception {
        if (game.isHighScore()) {
            drawButtons();
            TextInputDialog dialog = new TextInputDialog("Anonymous");
            dialog.setTitle("High score!");
            dialog.setHeaderText("Congratulations, you won!");
            dialog.setContentText("Please enter your name:");
            dialog.showAndWait();
            game.setHighscore(dialog.getEditor().getText());
            showHighscores(game.getDifficulty());
        }
    }

    private void showHighscores(int difficulty) throws Exception {
        String[] title = new String[] { "Easy", "Intermediate", "Hard", "Custom" };
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Top 10 High scores");
        alert.setHeaderText(title[difficulty]);
        alert.setContentText(game.getHighscores(difficulty));
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(MinesweeperGUI.class);
    }

}
