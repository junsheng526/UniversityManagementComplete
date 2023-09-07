package control;

import boundary.UniversityManagementUI;

/**
 *
 * @author Your Name A, B, C, D
 */
public class UniversityManagement {

    private final UniversityManagementUI universityUI = new UniversityManagementUI();

    public void runUniversityManagement() {
        // Main menu for University Management
        int choice;
        do {
            choice = universityUI.getMenuChoice();

            switch (choice) {
                case 1:
                    // Call Course Management subsystem
                    CourseManagement courseManagement = new CourseManagement();
                    courseManagement.runCourseManagement();
                    break;
                case 2:
                    // Call Programme Management subsystem
                    ProgrammeManagement programmeManagement = new ProgrammeManagement();
                    programmeManagement.runProgrammeManagement();
                    break;
                case 3:
                    // Call Tutor Management subsystem
                    TutorManagement tutorManagement = new TutorManagement();
                    tutorManagement.runTutorManagement();
                    break;
                case 4:
                    // Call Tutorial Group Management subsystem
                    TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
                    tutorialGroupManagement.runTutorialGroupManagement();
                    break;
                case 0:
                    System.out.println("Exiting University Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        UniversityManagement universityManagement = new UniversityManagement();
        universityManagement.runUniversityManagement();
    }
}
