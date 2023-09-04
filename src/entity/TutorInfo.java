package entity;

/**
 *
 * @author Your Name B
 */
import java.io.Serializable;

public class TutorInfo implements Serializable {

    private String tutorId;
    private String faculty;
    private String name;
    private String phoneNumber;
    private String gender;
    private String status;

    public TutorInfo() {

    }

    public TutorInfo(String tutorId, String faculty, String name, String phoneNumber, String gender, String status) {
        this.tutorId = tutorId;
        this.faculty = faculty;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.status = status;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-40s %-15s %-15s %-15s", tutorId, faculty, name, phoneNumber, gender, status);
    }
}
