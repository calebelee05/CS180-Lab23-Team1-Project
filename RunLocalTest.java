import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import static org.junit.Assert.*;

/**
 *
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Purdue CS
 * @version Dec 13, 2024
 */

public
class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent - all local tests ran successfully.\n");
        } else {
            System.out.printf("Tests failed: %d.\n",result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A framework to run public test cases.
     *
     * <p>Purdue University -- CS18000 -- Summer 2022</p>
     *
     * @author Purdue CS
     * @version June 13, 2023
     */

    public static class TestCase {
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }
        private String getOutput() {
            return testOut.toString();
        }
        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        @Test(timeout = 1000)
        public void testFunctions() {
            /*
             * User account creation, Item listing creation, Message user,
             * Buy Item, Sell Item, Delete Item listing
             */

            // Set the input
            String user1Input = "User1\nabcd1234\n1000.0\nItem1\n10.0\nTest Item1\nHello\nUser2\n";
            
            String user2Input = "User2\nqwer0000\n3500.0\nItem2\n15.0\nTest Item2\nHi\nUser1\n";
            
            // Pair the input with the expected result
            // User1: username, password, balance after purchase, Item listing, User2 received message, 
            String Expected = "User1" + System.lineSeparator() + "abcd1234" + System.lineSeparator() + "984.1" + 
                System.lineSeparator() + "Hi" + ;

            // Runs the program with the input values
            receiveInput(userInput);
            TestFunctions.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            output = output.replace("\r\n", "\n");
            expected = expected.replace("\r\n", "\n");
            assertEquals("Make sure your output matches the expected output",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testGetterSetters() {

            // Set the input
            String input = "User1" + System.lineSeparator() + "abcd1234" + System.lineSeparator() + "1000.0" +
                System.lineSeparator() + "Item1" + System.lineSeparator() + "10.0" + System.lineSeparator() +
                "Test Item1" + System.lineSeparator() + "Hello" + System.lineSeparator() + "User2" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = origin + System.lineSeparator() + make + System.lineSeparator() +
                    color + System.lineSeparator() + model + System.lineSeparator() +
                    year + System.lineSeparator() + destination + System.lineSeparator() +
                    fuel + System.lineSeparator() +
                    mpg + System.lineSeparator() + fin + "0001T015PS231NH63" + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            VinGenerator.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            output = output.replace("\r\n", "\n");
            expected = expected.replace("\r\n", "\n");
            assertEquals("Make sure your output matches the expected output",
                    expected.trim(), output.trim());

        }
    }
}