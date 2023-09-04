package entity;

/**
 *
 * @author Your Name C
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Tutor implements Comparable<Tutor>, Serializable {

    private TutorInfo tutor;
    private LocalDate dateAdded;

    public Tutor() {
    }

    public Tutor(String tutorId, String faculty, String name, String phoneNumber, String gender, String status) {
        this.tutor = new TutorInfo(tutorId, faculty, name, phoneNumber, gender, status);
        this.dateAdded = LocalDate.now();
    }

    public TutorInfo getTutorInfo() {
        return tutor;
    }

    public void setTutorInfo(TutorInfo tutor) {
        this.tutor = tutor;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int compareTo(Tutor T) {
        return this.tutor.getTutorId().compareTo(T.getTutorInfo().getTutorId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.tutor);
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
        Tutor other = (Tutor) obj;
        return this.tutor.getTutorId().equals(other.getTutorInfo().getTutorId());
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-40s %-15s %-15s %-15s %-15s", tutor.getTutorId(), tutor.getFaculty(), tutor.getName(), tutor.getPhoneNumber(), tutor.getGender(), tutor.getStatus(), dateAdded);
    }
}
