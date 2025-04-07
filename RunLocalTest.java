
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Running local tests using the test classes
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 6, 2025
 */

public class RunLocalTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestUser.class, TestItem.class, TestMessage.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("All tests passed successfully: " + result.wasSuccessful());
    }
}
