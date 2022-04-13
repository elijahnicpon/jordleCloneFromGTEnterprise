package jordle.main.jordlefinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;

public class MainScreenController {

    static Color themeLightGray = Color.web("ECECEC");
    static Color themeYellow = Color.web("FFBC2C");
    static Color themeGreen = Color.web("86B86B");
    static Color themeDarkGray = Color.web("4D4D4D");

    public class GuessGrid extends GridPane {
        void reset() {
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 5; col++) {
                    Rectangle square = new Rectangle(73, 73);
                    square.setFill(themeLightGray);
                    square.setStroke(Color.WHITE);
                    Text t = new Text("");
                    t.setFont(Font.font("Monospace", 68));
                    t.setFill(Color.WHITE);
                    StackPane sp = new StackPane(square, t);
                    sp.setAlignment(Pos.CENTER);
                    this.add(sp, col, row);
                }
            }
        }
        void commitAttempt(int attemptNumber, String guess, String solution) {
            for (int i = 0; i < 5; i++) {
                Rectangle square = new Rectangle(80, 80);
                square.setFill(themeDarkGray);
                square.setStroke(Color.WHITE);
                Text t = new Text(String.valueOf(guess.charAt(i)));
                t.setFont(Font.font("Monospace", 70));
                t.setFill(Color.WHITE);

                for (int j = 0; j < 5; j++) {
                    if (guess.charAt(i) == solution.charAt(j)) {
                        square.setFill(themeYellow);
                    }
                }

                if (guess.charAt(i) == solution.charAt(i)) {
                    square.setFill(themeGreen);
                }
                this.add(new StackPane(square, t), i, attemptNumber);
            }
        }
    }

    @FXML
    private TextField guessField;

    @FXML
    private Pane middlePane;


    @FXML
    private Text statusText;



    @FXML
    public void initialize() {
        statusText.setText("Initialized.");
//        {
//            AtomicReference<GuessGrid> guessGrid = new AtomicReference<GuessGrid>();
//            guessGrid.get().reset();
//            middlePane.getChildren().addAll(guessGrid.get());
//        }
        {
            GuessGrid guessGrid = new GuessGrid();
            guessGrid.reset();
            middlePane.getChildren().add(guessGrid);
        }




        //GuessField Stuff
        {
            guessField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (!"qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".contains(keyEvent.getCharacter())) {
                        keyEvent.consume();
                    }
                }
            });
            guessField.textProperty().addListener((ov, oldValue, newValue) -> {
                guessField.setText(newValue.toUpperCase());
            });
        }
    }

    @FXML
    public void viewInstructions(ActionEvent event) {
        Alert instructions = new Alert(Alert.AlertType.INFORMATION);
        {
            instructions.setTitle("Instructions:");
            StackPane logo;
            {
                Rectangle logoSquare = new Rectangle(80,80, Color.web("86B86B"));
                Text j = new Text("J");
                j.setFont(Font.font("Monospace", 70));
                j.setFill(Color.WHITE);
                logo = new StackPane(logoSquare, j);
            }
            instructions.setGraphic(logo);
            instructions.setHeaderText("Hey! Here's how to play Jordle:");
            instructions.setContentText("Jordle is just like Wordle!" +
                    "\nYou have to guess the Jordle in six goes or less." +
                    "\nEvery word you enter must be in the word list." +
                    "\nA correct letter turns green." +
                    "\nA correct letter in the wrong place turns yellow." +
                    "\nAn incorrect letter turns gray." +
                    "\nLetters can be used more than once." +
                    "\n\nBrought to you by Elijah Nicpon.");
        }
        instructions.showAndWait();
    }

    @FXML
    public void submitGuess(ActionEvent event) {
    }

    @FXML
    public void clearGuess(ActionEvent event) {
        guessField.clear();
        statusText.setText("Guess field cleared.");
    }
    @FXML
    public void reset(ActionEvent event) {
        //guessGrid
//        GuessGrid guessGrid = new GuessGrid();
//        guessGrid.reset();
//        middlePane.getChildren().addAll(guessGrid);
        statusText.setText("Game Reset.");
    }
}
