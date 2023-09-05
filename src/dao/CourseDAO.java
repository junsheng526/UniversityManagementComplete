package dao;

/**
 *
 * @author Deong Yue Jiaz
 */
import adt.ArrayList;
import entity.*;
import java.io.*;

public class CourseDAO {

    private String fileName = "courses.dat";

    public void saveToFile(ArrayList<Course> courseList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ArrayList<Course> retrieveFromFile() {
        File file = new File(fileName);
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (ArrayList<Course>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }
    }
}
