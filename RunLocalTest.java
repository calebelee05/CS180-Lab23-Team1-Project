import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunLocalTest {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestUser.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }

   public static class TestFunctions {
    // Set the input
    String user1Input = "User1\nabcd1234\n1000.0\nItem1\n10.0\nTest Item1\nHello\nUser2\n";
        
    String user2Input = "User2\nqwer0000\n3500.0\nItem2\n15.0\nTest Item2\nHi\nUser1\n";

    
    }
}  	