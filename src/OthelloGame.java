public class OthelloGame {
    private char[][] board = new char[8][8];
    private char currentPlayer = 'B';  // Black starts the game

    // Initialize the game board
    public OthelloGame() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }
        board[3][3] = 'W';
        board[4][4] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
    }

    // Display the current board
    public void displayBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // Validate and process a player's move
    public boolean isValidMove(int row, int col, char player) {
        // Implement the move validation logic based on Othello rules
        // For simplicity, return true (implement actual validation later)
        return board[row][col] == '.';
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';  // Switch turns
    }

    // Check if the game is over (no more valid moves)
    public boolean isGameOver() {
        // Simplified check: return false for now, can implement actual end-game condition later
        return false;
    }

    // Calculate and return the score
    public String getScore() {
        int blackCount = 0, whiteCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'B') blackCount++;
                if (board[i][j] == 'W') whiteCount++;
            }
        }
        return "Black: " + blackCount + " - White: " + whiteCount;
    }
}
