package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Your Name B
 */
public class TutorialGroup implements Serializable {

    private static final long serialVersionUID = 1L; // Unique identifier for serialization

    private String tutorialCode;
    private int year;
    private int semester;
    private int group;

    // Constructor
    public TutorialGroup(String tutorialCode, int year, int semester, int group) {
        this.tutorialCode = tutorialCode;
        this.year = year;
        this.semester = semester;
        this.group = group;
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
        return String.format("%-15s %-10d %-10d %-10d", tutorialCode, year, semester, group);
    }
}
