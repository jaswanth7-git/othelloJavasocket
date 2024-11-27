import java.io.*;
import java.net.*;
import java.util.*;

public class GameSession implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private OthelloGame game;

    public GameSession(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.game = new OthelloGame();
    }

    @Override
    public void run() {
        try {
            // Setup input and output streams for communication with the client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Inform the client that the game has started
            out.println("Welcome to Othello! Let's start the game.");

            // Main game loop
            while (true) {
                // Display current board state
                game.displayBoard();
                out.println("Your move (row,col): ");

                // Get client move (row,col) and process it
                String move = in.readLine();
                if (move == null || move.trim().isEmpty()) {
                    out.println("Invalid move. Please try again.");
                    continue;
                }

                // Parse move input
                String[] moveParts = move.split(",");
                if (moveParts.length != 2) {
                    out.println("Invalid input format. Please use (row,col).");
                    continue;
                }

                int row, col;
                try {
                    row = Integer.parseInt(moveParts[0].trim());
                    col = Integer.parseInt(moveParts[1].trim());
                } catch (NumberFormatException e) {
                    out.println("Invalid move. Please enter valid integers.");
                    continue;
                }

                // Validate the move
                if (!game.isValidMove(row, col, 'B')) {  // Assuming 'B' is the server's piece
                    out.println("Invalid move. Try again.");
                    continue;
                }

                // Make the move
                game.makeMove(row, col);

                // Check if the game is over
                if (game.isGameOver()) {
                    out.println("Game over! Final Score:");
                    out.println(game.getScore());
                    break;
                }

                // Send updated board to the client
                game.displayBoard();

                // Allow client to make a move
                out.println("Your move (row,col): ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Game session Error"+e.toString());
        } finally {
            // Close resources
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.out.println("Game session Error inside Finally "+e.toString());
                e.printStackTrace();
            }
        }
    }
}
