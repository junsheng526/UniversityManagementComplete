package entity;

/**
 *
 * @author Your Name D
 */
import adt.SortedArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Course implements Comparable<Course>, Serializable {

    private CourseInfo course;
    private LocalDate dateAdded;
    private SortedArrayList<Programme> programmes = new SortedArrayList<>();

    public Course() {
        programmes = new SortedArrayList<>();
    }

    public Course(String courseCode, String category, String name, int creditHours, String status) {
        this.course = new CourseInfo(courseCode, category, name, creditHours, status);
        this.dateAdded = LocalDate.now();
        this.programmes = new SortedArrayList<>();
    }

    public void addProgramme(Programme programme) {
        programmes.add(programme);
    }

    public boolean removeProgramme(Programme programme) {
        return programmes.remove(programme);
    }

    public SortedArrayList<Programme> getProgrammes() {
        return programmes;
    }

    public int getProgrammeCount() {
        return programmes.totalNumberOfObject();
    }

    public String listAllProgrammesByCourse() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < programmes.totalNumberOfObject(); i++) {
            Programme programme = programmes.getObject(i);
            outputStr.append(programme.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public CourseInfo getCourseInfo() {
        return course;
    }

    public void setCourseInfo(CourseInfo course) {
        this.course = course;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int compareTo(Course T) {
        return this.course.getCourseCode().compareTo(T.getCourseInfo().getCourseCode());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.course);
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
        Course other = (Course) obj;
        return this.course.getCourseCode().equals(other.getCourseInfo().getCourseCode())
                || this.course.getCategory().equals(other.getCourseInfo().getCategory())
                || this.course.getName().equals(other.getCourseInfo().getName())
                || this.course.getStatus().equals(other.getCourseInfo().getStatus());
    }

    @Override
    public String toString() {
        return String.format("%-100s %-15s %-15d", course.toString(), dateAdded, getProgrammeCount());
    }
}
