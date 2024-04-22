package org.game.mazesolver;

import java.util.Random;

/**
 * The type Maze.
 */
public class Maze {
    private int[][] mazeGrid;

    /**
     * Instantiates a new Maze.
     *
     * @param rows the rows
     * @param cols the cols
     */
    public Maze(int rows, int cols) {
        this.mazeGrid = new int[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        Random rand = new Random();
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[row].length; col++) {
                if (row == 0 || col == 0 || row == mazeGrid.length - 1 || col == mazeGrid[row].length - 1) {
                    mazeGrid[row][col] = 1;
                } else {
                    mazeGrid[row][col] = rand.nextInt(10) < 2 ? 1 : 0;
                }
            }
        }
    }

    /**
     * Get maze grid int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getMazeGrid() {
        return mazeGrid;
    }
}
