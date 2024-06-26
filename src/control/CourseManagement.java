package control;

/**
 *
 * @author Your Name D
 */
import adt.ArrayList;
import boundary.CourseManagementUI;
import dao.CourseDAO;
import entity.Course;
import entity.Programme;
import utility.MessageUI;

public class CourseManagement {

    private ArrayList<Course> courseList = new ArrayList<>(); // Use your custom ArrayList
    private final CourseManagementUI courseUI = new CourseManagementUI();
    private CourseDAO courseDAO = new CourseDAO();

    int add = 0, remove = 0, edit = 0;

    public CourseManagement() {
        courseList = courseDAO.retrieveFromFile();
    }

    public void runCourseManagement() {
        int choice;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 0 ->
                    MessageUI.displayExitMessage();
                case 1 -> {
                    addNewCourse();
                    courseUI.listAllCourses(getAllCourses());
                    courseUI.displayMessage("New course added.");
                    add++;
                }
                case 2 -> {
                    removeCourse();
                    courseUI.listAllCourses(getAllCourses());
                    remove++;
                }
                case 3 ->
                    findCourse();
                case 4 -> {
                    amendCourseDetails();
                    courseUI.listAllCourses(getAllCourses());
                    edit++;
                }
                case 5 ->
                    courseUI.listAllCourses(getAllCourses());
                case 6 ->
                    addProgrammeToCourse();
                case 7 ->
                    removeProgrammeFromCourse();
                case 8 ->
                    listAllProgrammesByCourse();
                case 9 ->
                    generateReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewCourse() {
        Course newCourse = courseUI.inputCourseDetails();
        courseList.add(newCourse);
        courseDAO.saveToFile(courseList);
    }

    public void removeCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseList.remove(course);
            courseDAO.saveToFile(courseList);
            courseUI.displayMessage("Course removed.");
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void findCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseUI.displayCourseDetails(course);
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void amendCourseDetails() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            courseUI.displayMessage("### Please enter new details ###");
            Course newCourseDetails = courseUI.inputCourseDetails();

            // Find the index of the course
            int index = courseList.getPositionOf(course);

            if (index >= 0) {
                courseList.replace(index, newCourseDetails);
                courseDAO.saveToFile(courseList);
                courseUI.displayMessage("Course details amended.");
            } else {
                courseUI.displayMessage("Course not found.");
            }
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void addProgrammeToCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            Programme newProgramme = courseUI.inputProgrammeDetails();
            course.addProgramme(newProgramme);
            courseDAO.saveToFile(courseList);
            courseUI.displayMessage("Programme added to the course.");
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public void removeProgrammeFromCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            String programmeCode = courseUI.inputProgrammeCode();
            Programme programme = findProgrammeByCode(course, programmeCode);
            if (programme != null) {
                course.getProgrammes().remove(programme);
                courseDAO.saveToFile(courseList);
                courseUI.displayMessage("Programme removed from the course.");
            } else {
                courseUI.displayMessage("Programme not found in the course.");
            }
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }

    public Programme findProgrammeByCode(Course course, String programmeCode) {
        for (int i = 1; i <= course.getProgrammes().getNumberOfEntries(); i++) {
            Programme programme = course.getProgrammes().getEntry(i);
            if (programmeCode.equals(programme.getProgrammeCode())) {
                return programme;
            }
        }
        return null;
    }

    public void generateReports() {
        courseUI.listAllCourses(getAllCourses());
        int totalCourse = courseList.getNumberOfEntries();
        courseUI.displayCourseManagementReport(totalCourse, add, remove, edit);
    }

    private Course findCourseByCode(String courseCode) {
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            if (course.getCourseInfo().getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private String getAllCourses() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            outputStr.append(course.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public void listAllProgrammesByCourse() {
        String courseCode = courseUI.inputCourseCode();
        Course course = findCourseByCode(courseCode);
        if (course != null) {
            String programs = course.listAllProgrammesByCourse();
            if (!programs.isEmpty()) {
                courseUI.listAllProgrammesByCourse(programs);
            } else {
                courseUI.displayMessage("No programs found for Course " + courseCode);
            }
        } else {
            courseUI.displayMessage("Course not found.");
        }
    }
}
