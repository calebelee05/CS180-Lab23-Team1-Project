import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Client class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
 */
public class Client implements Runnable, Communicator {

    // Fields
    Socket socket;
    private UserInterface user;

    // Methods
    @Override
    public void run() {
        // Set up server communication
        BufferedReader reader;
        PrintWriter writer;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            boolean running = true;
            while (running) {

            }

            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;

        try {
            Socket s = new Socket(host, port);
            Thread t = new Thread(new Client(s));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
