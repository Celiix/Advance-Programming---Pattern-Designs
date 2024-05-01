package org.game.mazesolver;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The type Maze solver.
 */
public class MazeSolver {
    private Maze maze;
    private boolean[][] visited;
    private Cell[][] parent;
    private int destinationX;
    private int destinationY;

    /**
     * Instantiates a new Maze solver.
     *
     * @param maze         the maze
     * @param destinationX the destination x
     * @param destinationY the destination y
     */
    public MazeSolver(Maze maze, int destinationX, int destinationY) {
        this.maze = maze; // Assigning a reference to the Maze object
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.visited = new boolean[maze.getMazeGrid().length][maze.getMazeGrid()[0].length];
        this.parent = new Cell[maze.getMazeGrid().length][maze.getMazeGrid()[0].length];
    }

    /**
     * Find shortest path linked list.
     *
     * @param startX the start x
     * @param startY the start y
     * @return the linked list
     */
    public LinkedList<Cell> findShortestPath(int startX, int startY) {
        Queue<Cell> queue = new LinkedList<>();
        LinkedList<Cell> path = new LinkedList<>();

        queue.add(new Cell(startX, startY));
        visited[startY][startX] = true;
        parent[startY][startX] = new Cell(-1, -1);

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            if (cell.x == destinationX && cell.y == destinationY) {
                Cell p = cell;
                while (parent[p.y][p.x].x != -1) {
                    path.addFirst(p);
                    p = parent[p.y][p.x];
                }
                return path;
            }

            for (int[] direction : directions) {
                int nextX = cell.x + direction[0];
                int nextY = cell.y + direction[1];

                if (isMoveValid(nextX, nextY) && !visited[nextY][nextX]) {
                    queue.add(new Cell(nextX, nextY));
                    visited[nextY][nextX] = true;
                    parent[nextY][nextX] = cell;
                }
            }
        }

        return path;
    }

    private boolean isMoveValid(int x, int y) {
        int[][] mazeGrid = maze.getMazeGrid();
        return x >= 0 && y >= 0 && x < mazeGrid[0].length && y < mazeGrid.length && mazeGrid[y][x] == 0;
    }

    /**
     * The type Cell.
     */
    static class Cell {
        /**
         * The X.
         */
        int x, /**
         * The Y.
         */
        y;

        /**
         * Instantiates a new Cell.
         *
         * @param x the x
         * @param y the y
         */
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
