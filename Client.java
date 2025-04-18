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
public class Client {

    public static void main(String[] args) {
        // Connect to server
        String host = ""; // placeholder
        int port = -1; // placeholder
        // actually connect to server

        // Set up server communication
        Socket socket;
        BufferedReader reader;
        PrintWriter writer;

        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            boolean running = true;
            while (running) {
                
            }

            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.printf("Could not connect to host \"%s\" at port \"%d\"\n", host, port);
        }
    }

    private void setUp() {

    }

}
