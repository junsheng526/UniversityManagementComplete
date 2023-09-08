package dao;

import adt.SinglyLinkedList;
import entity.TutorialGroup;
import java.io.*;

/**
 *
 * @author
 */
public class TutorialGroupDAO {

    private String fileName = "tutorialGroups.dat";

    public void saveToFile(SinglyLinkedList<TutorialGroup> groupList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(groupList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SinglyLinkedList<TutorialGroup> retrieveFromFile() {
        File file = new File(fileName);
        SinglyLinkedList<TutorialGroup> groupList = new SinglyLinkedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            groupList = (SinglyLinkedList<TutorialGroup>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return groupList;
        }
    }
}
