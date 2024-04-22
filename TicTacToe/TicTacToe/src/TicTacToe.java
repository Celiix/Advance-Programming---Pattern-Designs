import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 * Main class representing the Tic Tac Toe game.
 */
public class TicTacToe extends Application {
    private Game game_obj;
    private Button[][] buttons_Obj = new Button[3][3];
    private Label turnLbl_obj;

    /**
     * Method to start the JavaFX application.
     * @param primaryStage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        game_obj = new Game();
        game_obj.setState(new InProgressState());

        GridPane grid_Pane_Obj = new GridPane();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button_Obj = new Button();
                button_Obj.setMinSize(100, 100);
                int final_Row_Obj = row;
                int final_Col_Obj = col;
                button_Obj.setOnAction(event -> handleButtonClick(final_Row_Obj, final_Col_Obj));
                buttons_Obj[row][col] = button_Obj;
                button_Obj.setFocusTraversable(false);

                grid_Pane_Obj.add(button_Obj, col, row);
            }
        }
        turnLbl_obj = new Label("Current Turn: " + game_obj.getCurrentPlayer()); // Initialize label with initial turn

        Button restart_Btn_Obj = new Button("Restart");
        restart_Btn_Obj.setOnAction(event -> resetGameButton());

        HBox hbox_Obj = new HBox(turnLbl_obj, restart_Btn_Obj);
        hbox_Obj.setSpacing(10);

        Scene scene_obj = new Scene(new GridPane(), 300, 350);
        ((GridPane) scene_obj.getRoot()).add(grid_Pane_Obj, 0, 0);
        ((GridPane) scene_obj.getRoot()).add(hbox_Obj, 0, 1);

        primaryStage.setScene(scene_obj);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    /**
     * Handles the button click event for each cell in the game grid.
     * @param row The row index of the clicked cell.
     * @param col The column index of the clicked cell.
     */
    private void handleButtonClick(int row, int col) {
        char currentPly = game_obj.getCurrentPlayer();
        game_obj.handlePlayerMove(game_obj, row, col, currentPly);
        updateButton(row, col, currentPly);
        if (game_obj.isGameOver()) {
            displayGameResult();
        }
        turnLbl_obj.setText("Current Turn: " + game_obj.getCurrentPlayer());

    }

    /**
     * Updates the button with a fade animation when a move is made.
     * @param row The row index of the updated cell.
     * @param col The column index of the updated cell.
     * @param player The player symbol.
     */
    private void updateButton(int row, int col, char player) {
        Button button_Obj = buttons_Obj[row][col];
        button_Obj.setText(String.valueOf(player));
        button_Obj.setDisable(true);

        // Add fade animation to the button
        FadeTransition fade_Trans = new FadeTransition(Duration.millis(500), button_Obj);
        fade_Trans.setFromValue(0);
        fade_Trans.setToValue(1);
        fade_Trans.play();
    }

    /**
     * Displays the game result in an alert dialog.
     */
    private void displayGameResult() {
        System.out.println("calling");
        Alert alert_Obj = new Alert(AlertType.INFORMATION);
        alert_Obj.setTitle("Game Result");
        if (game_obj.isDraw()) {
            alert_Obj.setHeaderText("It's a draw!");
        } else {
            char winner = game_obj.getWinner();
            if (winner == ' ') {
                alert_Obj.setHeaderText("No winner!");
            } else {
                alert_Obj.setHeaderText(winner + " wins!");
            }
        }
        alert_Obj.setContentText("Click OK to play again.");
        alert_Obj.showAndWait();
        resetGameButton();
    }

    /**
     * Resets the game to its initial state.
     */
    private void resetGameButton() {
        for (Button[] buttonRow : buttons_Obj) {
            for (Button button : buttonRow) {
                button.setText("");
                button.setDisable(false);
            }
        }
        game_obj.reset();
        turnLbl_obj.setText("Current Turn: " + game_obj.getCurrentPlayer());
    }

    /**
     * The main method to launch the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
