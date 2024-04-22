/**
 * Represents the state of the Tic Tac Toe game while it is in progress.
 */
public class InProgressState implements GameState {
    private char[][] board = new char[3][3];
    private boolean playerX = true;

    /**
     * Handles a player's move in the game.
     * @param game The game object.
     * @param row The row where the player wants to move.
     * @param col The column where the player wants to move.
     * @param player The symbol of the player making the move.
     */
    @Override
    public void handlePlayerMove(Game game, int row, int col, char player) {
        if (isValidMove(row, col)) {
            board[row][col] = player;
            display();
            if (isWinner(player, row, col) || isBoardFull()) {
                displayResult(game, player);
            } else {
                playerX = !playerX;
            }
        } else {
            displayInvalidMove();
        }
    }

    /**
     * Displays the current state of the game board.
     */
    @Override
    public void display() {
        System.out.println("Current Board:");
        for (char[] rowArray : board) {
            for (char cell : rowArray) {
                if (cell == 0) {
                    System.out.print("_ ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == 0;
    }

    private boolean isWinner(char player, int row, int col) {
        return (board[row][0] == player && board[row][1] == player && board[row][2] == player) || // Horizontal
                (board[0][col] == player && board[1][col] == player && board[2][col] == player) || // Vertical
                (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) || // Diagonal
                (row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player); // Anti-diagonal
    }

    boolean isBoardFull() {
        for (char[] rowArray : board) {
            for (char cell : rowArray) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayResult(Game game, char player) {
        if (isWinner(player, 1, 1)) {
            System.out.println(player + " wins!");
        } else if (isBoardFull()) {
            System.out.println("It's a draw!");
        }
        // Set game state to GameOverState
        GameOverState gameOverState = new GameOverState();
        if (isWinner(player, 1, 1)) {
            gameOverState.setWinner(player);
        } else if (isBoardFull()) {
            gameOverState.setDraw(true);
        }
        game.setState(gameOverState);
    }

    private void displayInvalidMove() {
        System.out.println("Invalid move. Please try again.");
    }
}
