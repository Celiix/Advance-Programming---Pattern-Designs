/**
 * Represents the Tic Tac Toe game.
 */
public class Game {
    private GameState currentStateGame;
    private char currentPlayer = 'X';

    /**
     * Sets the current state of the game.
     * @param state The state to set.
     */
    public void setState(GameState state) {
        this.currentStateGame = state;
    }

    /**
     * Handles a player's move in the game.
     * @param game The game object.
     * @param row The row where the player wants to move.
     * @param col The column where the player wants to move.
     * @param player The symbol of the player making the move.
     */
    public void handlePlayerMove(Game game, int row, int col, char player) {
        currentStateGame.handlePlayerMove(game, row, col, player);
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Gets the symbol of the current player.
     * @return The symbol of the current player.
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game is over.
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return currentStateGame instanceof GameOverState || currentStateGame instanceof InProgressState && ((InProgressState) currentStateGame).isBoardFull();
    }

    /**
     * Checks if the game ended in a draw.
     * @return True if the game is a draw, false otherwise.
     */
    public boolean isDraw() {
        return currentStateGame instanceof GameOverState && ((GameOverState) currentStateGame).isDraw();
    }

    /**
     * Gets the winner of the game.
     * @return The symbol of the winner, or a space if there is no winner.
     */
    public char getWinner() {
        return currentStateGame instanceof GameOverState ? ((GameOverState) currentStateGame).getWinner() : ' ';
    }

    /**
     * Resets the game to its initial state.
     */
    public void reset() {
        currentStateGame = new InProgressState();
        currentPlayer = 'X';
    }
}
