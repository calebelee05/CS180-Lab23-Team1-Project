import java.io.IOException;

public interface ClientInterface {
    Object sendRequest(String... request) throws IOException;
    void close() throws IOException;
}
