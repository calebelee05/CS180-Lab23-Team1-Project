import static org.junit.Assert.*;
import org.junit.Test;
import java.net.Socket;

public class TestServer {

    @Test
    public void testSocketStoringConstructor() throws Exception {
        Socket dummySocket = new Socket();
        Server server = new Server(dummySocket);
        assertSame(dummySocket, server.getSocket());
    }
}
