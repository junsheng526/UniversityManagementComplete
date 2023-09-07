package entity;

import adt.SinglyLinkedList;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Your Name B
 */
public class TutorialGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tutorialCode;
    private int year;
    private int semester;
    private int group;
    private SinglyLinkedList<Student> students;

    // Constructor
    public TutorialGroup(String tutorialCode, int year, int semester, int group) {
        this.tutorialCode = tutorialCode;
        this.year = year;
        this.semester = semester;
        this.group = group;
        this.students = new SinglyLinkedList<>();
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    // Add a student to the tutorial group
    public void addStudent(Student student) {
        students.add(student);
    }

    // Get the list of students in the tutorial group
    public SinglyLinkedList<Student> getStudents() {
        return students;
    }

    public String listAllStudentsByGroup() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
            Student student = students.getEntry(i);
            outputStr.append(student.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public int getStudentCount() {
        return students.getNumberOfEntries();
    }

    public Student findStudentByID(String studentID) {
        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
            Student student = students.getEntry(i);
            if (student.getStudentID().equals(studentID)) {
                return student; // Found the student with the given ID
            }
        }
        return null; // Student with the given ID not found
    }

    public void removeStudent(Student student) {
        int position = students.search(student);
        if (position != 0){
            students.remove(position);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TutorialGroup other = (TutorialGroup) obj;
        return tutorialCode.equals(other.tutorialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorialCode);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-10d %-10d %-10d %-20d", tutorialCode, year, semester, group, getStudentCount());
    }
}
