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
public class Writable<T extends Serializable>
{   
    //method to write objects into the file
    public void writeObject(String fileName, ArrayList<T> list) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(list);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //method to read the objects from the file
    public ArrayList<T> readObject(String fileName) {
        ArrayList<T> objects = new ArrayList<>();
        try {
            File f = new File(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                objects = (ArrayList<T>) ois.readObject();
                return objects;
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return objects;
    }
}
