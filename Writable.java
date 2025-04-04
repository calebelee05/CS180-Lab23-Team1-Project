import java.io.*;
import java.util.*;

/**
 * The Writable interface.
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Anishka Rao
 * @version April 1, 2025
 */
public interface Writable<T extends Serializable> {   
    //method to write objects into the file
    void writeObject(ArrayList<T> list); 

    //method to read the objects from the file
    ArrayList<T> readObject();
}