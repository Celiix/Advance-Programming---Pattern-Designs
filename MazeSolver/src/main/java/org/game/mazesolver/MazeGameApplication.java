package org.game.mazesolver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

/**
 * The type Maze game application.
 */
public class MazeGameApplication extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CELL_SIZE = 30;

    private Maze maze;
    private Player player;
    private GraphicsContext gc;

    private Image playerImage = new Image(String.valueOf(getClass().getResource("player.png").toURI()));
    private Image destinationImage = new Image(String.valueOf(getClass().getResource("destination.png").toURI()));
    private int destinationX, destinationY;
    private MazeSolver mazeSolver;

    private boolean reachedDestination = false;

    private int initialPlayerX, initialPlayerY;

    /**
     * Instantiates a new Maze game application.
     *
     * @throws URISyntaxException the uri syntax exception
     */
    public MazeGameApplication() throws URISyntaxException {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Maze Game");

        maze = new Maze(HEIGHT / CELL_SIZE, WIDTH / CELL_SIZE);

        Random rand = new Random();
        maze = new Maze(HEIGHT / CELL_SIZE, WIDTH / CELL_SIZE);

        int playerX, playerY;
        do {
            playerX = rand.nextInt(maze.getMazeGrid()[0].length - 2) + 1;
            playerY = rand.nextInt(maze.getMazeGrid().length - 2) + 1;
        } while (maze.getMazeGrid()[playerY][playerX] == 1);
        player = new Player(playerX, playerY); //Creating a new Player object and storing its reference

        initialPlayerX = playerX;
        initialPlayerY = playerY;

        do {
            destinationX = rand.nextInt(maze.getMazeGrid()[0].length - 2) + 1;
            destinationY = rand.nextInt(maze.getMazeGrid().length - 2) + 1;
        } while ((maze.getMazeGrid()[destinationY][destinationX] == 1) || (destinationX == playerX && destinationY == playerY)); // Ensure the position is not a wall and not the same as the player's

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        mazeSolver = new MazeSolver(maze, destinationX, destinationY);

        drawMaze();

        StackPane root = new StackPane();
        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (isMoveValid(player.getPosX(), player.getPosY() - 1)) player.moveUp();
                    break;
                case DOWN:
                    if (isMoveValid(player.getPosX(), player.getPosY() + 1)) player.moveDown();
                    break;
                case LEFT:
                    if (isMoveValid(player.getPosX() - 1, player.getPosY())) player.moveLeft();
                    break;
                case RIGHT:
                    if (isMoveValid(player.getPosX() + 1, player.getPosY())) player.moveRight();
                    break;
            }
            if (player.getPosX() == destinationX && player.getPosY() == destinationY) {
                reachedDestination = true;
                player.setPosX(initialPlayerX);
                player.setPosY(initialPlayerY);
                showVictoryMessage();
            }
            drawMaze();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showVictoryMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText(null);
        alert.setContentText("You've reached the destination!");

        alert.showAndWait();
    }


    private void drawMaze() {
        int[][] mazeGrid = maze.getMazeGrid();
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[row].length; col++) {
                if (mazeGrid[row][col] == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        if (reachedDestination) {
            gc.setFill(Color.INDIANRED);
            List<MazeSolver.Cell> shortestPath = mazeSolver.findShortestPath(player.getPosX(), player.getPosY());
            for (MazeSolver.Cell cell : shortestPath) {
                gc.fillRect(cell.x * CELL_SIZE, cell.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        gc.drawImage(destinationImage, destinationX * CELL_SIZE, destinationY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        gc.drawImage(playerImage, player.getPosX() * CELL_SIZE, player.getPosY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }


    private boolean isMoveValid(int x, int y) {
        return maze.getMazeGrid()[y][x] == 0;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
