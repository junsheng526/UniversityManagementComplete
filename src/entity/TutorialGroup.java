package entity;

import adt.ArrayStack;
import java.io.Serializable;

/**
 *
 * @author Your Name B
 */
public class TutorialGroup implements Serializable {

    private static final long serialVersionUID = 1L; // Unique identifier for serialization

    private String tutorialCode;
    private int year;
    private String semester;
    private String group;
    private ArrayStack<Student> students;

    // Constructor
    public TutorialGroup(String tutorialCode, int year, String semester, String group, int initialCapacity) {
        this.tutorialCode = tutorialCode;
        this.year = year;
        this.semester = semester;
        this.group = group;
        students = new ArrayStack<>(initialCapacity);
    }

    // Add a student to the tutorial group
    public void addStudent(Student student) {
        students.push(student);
    }

    // Remove a student from the tutorial group
    public void removeStudent() {
        students.pop();
    }
    
    public String getTutorialCode() {
        return tutorialCode;
    }

    public void setTutorialCode(String tutorialCode) {
        this.tutorialCode = tutorialCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    // Getters and setters for students
    public ArrayStack<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayStack<Student> students) {
        this.students = students;
    }
}
