/**
 * Interface representing the state of the Tic Tac Toe game.
 */
public interface GameState {
    /**
     * Handles a player's move in the game.
     * @param game The game object.
     * @param row The row where the player wants to move.
     * @param col The column where the player wants to move.
     * @param player The symbol of the player making the move.
     */
    void handlePlayerMove(Game game, int row, int col, char player);

    /**
     * Displays the current state of the game.
     */
    void display();
}
