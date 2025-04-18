
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

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String sendRequest(String... request) throws IOException {
        for (String line : request) {
            writer.println(line); // Send each line of the request
        }
        return reader.readLine(); // Read server's response
    }

    public void close() throws IOException {
        socket.close();
        writer.close();
        reader.close();
    }
}
