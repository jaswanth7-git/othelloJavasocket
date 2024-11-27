import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class OthelloServer {
    private static final int PORT = 12345;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);  // Handle up to 10 clients

    public static void main(String[] args) {
        System.out.println("Othello Server is starting...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                // Handle the client connection in a separate thread
                threadPool.submit(new GameSession(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Connection Lost !");
            e.printStackTrace();
        }
    }
}
