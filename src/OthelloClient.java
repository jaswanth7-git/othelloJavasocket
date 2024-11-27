import java.io.*;
import java.net.*;

public class OthelloClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter writer;

    public static void main(String[] args) {
        try {
            // Connect to the server
            socket = new Socket(SERVER_ADDRESS, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Read and print the game board
            String board = reader.readLine();  // Assume the server sends the board first
            System.out.println(board);

            // Loop to send moves
            while (true) {
                System.out.print("Enter your move (row,col): ");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String move = input.readLine();
                writer.println(move);  // Send move to the server

                // Get the updated game board
                board = reader.readLine();
                System.out.println(board);

                // Check for game end
                String gameResult = reader.readLine();
                if (gameResult != null) {
                    System.out.println(gameResult);
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Server Connection Lost !");
            e.printStackTrace();
        }
    }
}
