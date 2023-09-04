package entity;

/**
 *
 * @author Your Name B
 */
import java.io.Serializable;

public class TutorialGroupInfo implements Serializable {

    private String tutorialGroupCode;
    private int year;
    private int sem;

    public TutorialGroupInfo() {

    }

    public TutorialGroupInfo(String tutorialGroupCode, int year, int sem) {
        this.tutorialGroupCode = tutorialGroupCode;
        this.year = year;
        this.sem = sem;
    }

    public String getTutorialGroupCode() {
        return tutorialGroupCode;
    }

    public void setTutorialGroupCode(String tutorialGroupCode) {
        this.tutorialGroupCode = tutorialGroupCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-5d %-5d", tutorialGroupCode, year, sem);
    }
}
