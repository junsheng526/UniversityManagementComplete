package dao;

/**
 *
 * @author Your Name B
 */
import adt.SortedArrayList;
import entity.Tutor;
import java.io.*;

public class TutorDAO {

    private String fileName = "tutors.dat";

    public void saveToFile(SortedArrayList<Tutor> tutorList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SortedArrayList<Tutor> retrieveFromFile() {
        File file = new File(fileName);
        SortedArrayList<Tutor> tutorList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutorList = (SortedArrayList<Tutor>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tutorList;
        }
    }
}

