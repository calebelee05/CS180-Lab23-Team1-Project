import static org.junit.Assert.*;
import org.junit.Test;
import java.net.Socket;

/**
 * A JUnit test class for the Server Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 19, 2025
 */

public class TestServer {

    // test that the constructor properly stores the socket
    @Test
    public void testSocketStoringConstructor() throws Exception {
        Socket dummySocket = new Socket();
        Server server = new Server(dummySocket);
        assertSame("Constructor should keep the exact instance of Socket", dummySocket, server.getSocket());
    }

    // tests the socket accessor
    @Test
    public void testGetters() throws Exception {
        Socket dummySocket = new Socket();
        Server server = new Server(dummySocket);
        Socket returns = server.getSocket();
        assertNotNull("getSocket() should not return null", returns);
        assertSame("getSocket() should return the same instance", dummySocket, returns);
    }

}
