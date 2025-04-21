
import org.junit.Test;
import java.net.*;
import java.io.*;

/**
 * A JUnit test class for the Client Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 19, 2025
 */
public class TestClient {

    // tests that the constructor throws an IOException if it cannot connect
    @Test(expected = IOException.class) // tells JUnit expect an IO Exception
    public void testConstructorThrowsInvalidHost() throws IOException {
        new Client("no.exact.host", 1234);
    }

    // tests close() does not throw when opening a successful connection
    @Test
    public void testNoThrowOnClose() throws Exception {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            int port = serverSocket.getLocalPort();
            // starting thread to accept one connection, send header, close
            Thread server = new Thread(new Runnable() {
                public void run() {
                    try {
                        Socket inc = serverSocket.accept();
                        ObjectOutputStream oos = new ObjectOutputStream(inc.getOutputStream());
                        oos.flush();
                        inc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            // client should connect successfully?
            server.start();
            Client client = new Client("localhost", port);
            client.close(); // should not throw

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
