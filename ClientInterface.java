
import java.io.IOException;

/**
 * A User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version March 31, 2025
 */
public interface ClientInterface {

    Object sendRequest(String... request) throws IOException;

    void close() throws IOException;
}
