package minesweeper.ui;

import javafx.animation.AnimationTimer;
import minesweeper.engine.Game;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

    private GridPane board;
    private Game game;
    private BorderPane layout;
    private BorderPane topRow;
    private VBox leftColumn;
    private Font font;
    private Label clock;
    private Button newGame;
    private RadioButton easy;
    private RadioButton intermediate;
    private RadioButton hard;
    private RadioButton custom;
    private Slider width;
    private Slider height;
    private Slider mines;

    @Override
    public void init() {
        game = new Game(0);
        layout = new BorderPane();
        topRow = new BorderPane();
        leftColumn = new VBox();
        font = Font.font("Monospaced", 15);
    }

    /**
     * Creates the layout.
     */
    @Override
    public void start(Stage stage) throws Exception {
        drawTopRow();
        drawLeftColumn();
        board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        drawButtons();
        layout.setCenter(board);
        newGame.setOnAction(actionEvent -> {
            clock.setText("0");
            if (easy.isSelected()) {
                game = new Game(0);
            }
            if (intermediate.isSelected()) {
                game = new Game(1);
            }
            if (hard.isSelected()) {
                game = new Game(2);
            }
            if (custom.isSelected()) {
                game = new Game((int) width.getValue(), (int) height.getValue(), (int) mines.getValue());
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
        easy = new RadioButton("Easy");
        intermediate = new RadioButton("Intermediate");
        hard = new RadioButton("Hard");
        custom = new RadioButton("Custom:");

        ToggleGroup radioGroup = new ToggleGroup();
        easy.setToggleGroup(radioGroup);
        intermediate.setToggleGroup(radioGroup);
        hard.setToggleGroup(radioGroup);
        custom.setToggleGroup(radioGroup);
        easy.setSelected(true);

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
        leftColumn.getChildren().addAll(newGame, easy, intermediate, hard, custom);
        leftColumn.setSpacing(5);
        leftColumn.getChildren().addAll(new HBox(new Label("Width: "), widthValue), width,
                new HBox(new Label("Height: "), heightValue), height, new HBox(new Label("Mines: "), minesValue),
                mines);
        layout.setLeft(leftColumn);
    }

    private void drawButtons() {
        board.getChildren().clear();
        //Image image = new Image("file:kolmio.jpg");

        for (int y = 0; y < game.getGameState().length; y++) {
            for (int x = 0; x < game.getGameState()[0].length; x++) {
                Button cell = new Button(String.valueOf(game.getGameState()[y][x]));
                cell.setFont(font);
                board.add(cell, x, y);
                cell.setFocusTraversable(false);
                //ImageView imageView = new ImageView(image);

                cell.setMinSize(30, 30);
                cell.setMaxSize(30, 30);

                // cell.setStyle("-fx-focus-color: transparent;");
                // cell.setStyle("-fx-faint-focus-color: transparent;");
                if (game.getGameState()[y][x] != '#' && game.getGameState()[y][x] != 'F') {
                    cell.setDisable(true);
                    cell.setStyle("-fx-text-fill: #000000");
                    // cell.setStyle("-fx-background-color: #ff00ff");
                } else if (game.getGameState()[y][x] == '#') {
                    cell.setText(" ");
                    // cell.setGraphic(imageView);
                } else {
                    cell.setText("F");
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
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            game.flagCell(rx, ry);
                        }
                        drawButtons();
                    });
                }
            }
        }
    }

    private void checkIfHighScore() throws Exception {
        if (game.isHighScore()) {
            TextInputDialog dialog = new TextInputDialog("walter");
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("Please enter your name:");
            dialog.show();
        }
    }

    public static void main(String[] args) {
        launch(MinesweeperGUI.class);
    }

}