package dao;

/**
 *
 * @author Your Name B
 */
import adt.SortedArrayList;
import entity.*;
import java.io.*;

public class TutorialGroupDAO {

    private String fileName = "tutorialgroups.dat";

    public void saveToFile(SortedArrayList<TutorialGroup> tutorialGroupList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorialGroupList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SortedArrayList<TutorialGroup> retrieveFromFile() {
        File file = new File(fileName);
        SortedArrayList<TutorialGroup> tutorialGroupList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutorialGroupList = (SortedArrayList<TutorialGroup>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tutorialGroupList;
        }
    }
}

