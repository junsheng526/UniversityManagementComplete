package entity;

/**
 *
 * @author Your Name B
 */
import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {

    private String studentID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String tutorialGroup;
    private String courses;

    public Student() {
    }

    public Student(String studentID, String firstName, String lastName, int age, String gender, String tutorialGroup, String courses) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.tutorialGroup = tutorialGroup;
        this.courses = courses;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    @Override
    public int compareTo(Student student) {
        return this.getStudentID().compareTo(student.getStudentID());
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-40s %-15s %-15s %-15s %-15s", studentID, firstName, lastName, age, gender, tutorialGroup, courses);
    }
}

