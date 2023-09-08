package dao;

/**
 *
 * @author
 */
import adt.DoubleLinkedQueue;
import entity.Programme;
import java.io.*;

public class ProgrammeDAO {

    private String fileName = "programmes.dat";

    public void saveToFile(DoubleLinkedQueue<Programme> programmeList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(programmeList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public DoubleLinkedQueue<Programme> retrieveFromFile() {
        File file = new File(fileName);
        DoubleLinkedQueue<Programme> programmeList = new DoubleLinkedQueue<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            programmeList = (DoubleLinkedQueue<Programme>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return programmeList;
        }
    }
}
