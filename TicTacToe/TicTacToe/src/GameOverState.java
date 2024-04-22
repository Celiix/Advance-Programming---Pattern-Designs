/**
 * Represents the state of the game when it's over.
 */
public class GameOverState implements GameState {
    private boolean draw;
    private char winner;

    /**
     * Handles a player's move in the game (no action in game over state).
     * @param game The game object.
     * @param row The row where the player wants to move.
     * @param col The column where the player wants to move.
     * @param player The symbol of the player making the move.
     */
    @Override
    public void handlePlayerMove(Game game, int row, int col, char player) {
        // No action in game over state
    }

    /**
     * Displays the result of the game.
     */
    @Override
    public void display() {
        if (draw) {
            System.out.println("It's a draw!");
        } else {
            System.out.println(winner + " wins!");
        }
    }

    /**
     * Checks if the game ended in a draw.
     * @return True if the game ended in a draw, false otherwise.
     */
    public boolean isDraw() {
        return draw;
    }

    /**
     * Gets the winner of the game.
     * @return The symbol of the winner, or a space if there is no winner.
     */
    public char getWinner() {
        return winner;
    }

    /**
     * Sets whether the game ended in a draw.
     * @param draw True if the game ended in a draw, false otherwise.
     */
    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    /**
     * Sets the winner of the game.
     * @param winner The symbol of the winning player.
     */
    public void setWinner(char winner) {
        this.winner = winner;
    }
}
