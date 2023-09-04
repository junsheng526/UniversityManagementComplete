package entity;

/**
 *
 * @author Your Name B
 */
import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {

    private String studentID;
    private String name;

    public Student() {
    }

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student student) {
        return this.getStudentID().compareTo(student.getStudentID());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-40s", studentID, name);
    }
}
