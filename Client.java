
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class Client implements ClientInterface {

    private final Socket socket;
    private final PrintWriter writer;
    private final ObjectInputStream ois;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object sendRequest(String... request) throws IOException {
        try {
            for (String line : request) {
                writer.println(line); // Send each line of the request
            }
            writer.flush();
            return ois.readObject(); // Read server's response
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to read response from server", e);
        }
    }

    public void close() throws IOException {
        socket.close();
        writer.close();
        ois.close();
    }
}
