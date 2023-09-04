package entity;

/**
 *
 * @author Your Name B
 */
import adt.SortedArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TutorialGroup implements Comparable<TutorialGroup>, Serializable {

    private TutorialGroupInfo tutorialGroup;
    private LocalDate dateAdded;
    private SortedArrayList<Student> students = new SortedArrayList<>();

    public TutorialGroup() {
        students = new SortedArrayList<>();
    }

    public TutorialGroup(String tutorialGroupCode, int year, int sem) {
        this.tutorialGroup = new TutorialGroupInfo(tutorialGroupCode, year, sem);
        this.dateAdded = LocalDate.now();
        this.students = new SortedArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    public SortedArrayList<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.totalNumberOfObject();
    }

    public String listAllStudents() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < students.totalNumberOfObject(); i++) {
            Student student = students.getObject(i);
            outputStr.append(student.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public TutorialGroupInfo getTutorialGroupInfo() {
        return tutorialGroup;
    }

    public void setTutorialGroupInfo(TutorialGroupInfo tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int compareTo(TutorialGroup other) {
        return this.tutorialGroup.getTutorialGroupCode().compareTo(other.getTutorialGroupInfo().getTutorialGroupCode());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.tutorialGroup);
        return hash;
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
        return this.tutorialGroup.getTutorialGroupCode().equals(other.getTutorialGroupInfo().getTutorialGroupCode());
    }

    @Override
    public String toString() {
        return String.format("%-35s %-15s %-20d", tutorialGroup.toString(), dateAdded, getStudentCount());
    }
}
