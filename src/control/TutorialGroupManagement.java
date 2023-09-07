package control;

/**
 *
 * @author Your Name B
 */
import adt.ArrayStack;
import adt.ListInterface;
import adt.SinglyLinkedList;
import boundary.TutorialGroupManagementUI;
import entity.Student;
import entity.TutorialGroup;
import utility.MessageUI;

public class TutorialGroupManagement {

    private SinglyLinkedList<TutorialGroup> tutorialGroups = new SinglyLinkedList<>();
    private final TutorialGroupManagementUI tutorialUI = new TutorialGroupManagementUI();

    public ListInterface<TutorialGroup> initializeTutorialGroups() {
        tutorialGroups.add(new TutorialGroup("RSW", 3, 1, 1));
        tutorialGroups.add(new TutorialGroup("RSD", 1, 1, 1));
        tutorialGroups.add(new TutorialGroup("RAC", 3, 2, 1));
        return tutorialGroups;
    }

    public void runTutorialGroupManagement() {
        ListInterface<TutorialGroup> tutorialGroups = initializeTutorialGroups();

        int choice = 0;
        do {
            choice = tutorialUI.getMenuChoice();
            switch (choice) {
                case 0:
                    tutorialUI.displayMessage("Exiting the Tutorial Group Management System.");
                    break;
                case 1:
                    addStudentToTutorialGroup();
                    break;
                case 2:
                    removeStudentFromTutorialGroup();
                    break;
                case 3:
                    changeTutorialGroupForStudent();
                    break;
                case 4:
                    findStudentInTutorialGroup();
                    break;
                case 5:
                    listAllStudentsInTutorialGroup();
                    break;
                case 6:
                    filterTutorialGroupsBasedOnCriteria();
                    break;
                case 7:
                    tutorialUI.listAllTutorialGroups(displayTutGroup());
                    break;
                case 8:
                    generateTutorialGroupReports();
                    break;
                default:
                    tutorialUI.displayMessage("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);
    }

    public void removeStudentFromTutorialGroup() {
        // Get the tutorial group code from the user
        String tutorialCode = tutorialUI.inputTutorialGroupCode();

        // Find the tutorial group by code
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialCode);

        if (tutorialGroup != null) {
            // Check if the tutorial group has any students
            if (tutorialGroup.getStudentCount() > 0) {
                // Prompt the user to enter the student ID to remove
                String studentIDToRemove = tutorialUI.inputStudentID();

                // Check if the student exists in the tutorial group
                Student studentToRemove = tutorialGroup.findStudentByID(studentIDToRemove);

                if (studentToRemove != null) {
                    // Remove the student from the tutorial group
                    tutorialGroup.removeStudent(studentToRemove);

                    tutorialUI.displayMessage("Student removed from the tutorial group.");
                } else {
                    tutorialUI.displayMessage("Student not found in the tutorial group.");
                }
            } else {
                tutorialUI.displayMessage("No students in the tutorial group.");
            }
        } else {
            tutorialUI.displayMessage("Tutorial group not found.");
        }
    }

    public Student findStudentExisting(String studentID) {
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            SinglyLinkedList<Student> students = tutorialGroup.getStudents();

            if (students != null) {
                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j);
                    if (student.getStudentID().equals(studentID)) {
                        return student;
                    }
                }
            }
        }
        return null;
    }

    public TutorialGroup findTutorialGroupByStudent(String studentID) {
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            SinglyLinkedList<Student> students = tutorialGroup.getStudents();

            if (students != null) {
                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j);
                    if (student.getStudentID().equals(studentID)) {
                        return tutorialGroup; // Return the tutorial group containing the student
                    }
                }
            }
        }
        return null; // Student not found in any tutorial group
    }

    public void changeTutorialGroupForStudent() {
        // Prompt the user for the student's ID and the new tutorial group code
        tutorialUI.inputChangesDetails();
        String studentID = tutorialUI.inputStudentID();
        String newTutorialGroupCode = tutorialUI.inputTutorialGroupCode();

        // Find the student in the current tutorial groups
        Student studentToMove = findStudentExisting(studentID);
        if (studentToMove == null) {
            tutorialUI.displayMessage("Student not found.");
            return;
        }

        // Find the new tutorial group by code
        TutorialGroup newGroup = findTutorialGroupByCode(newTutorialGroupCode);
        if (newGroup == null) {
            tutorialUI.displayMessage("New tutorial group not found.");
            return;
        }

        // Remove the student from the current tutorial group
        TutorialGroup currentGroup = findTutorialGroupByStudent(studentID);
        if (currentGroup == null) {
            tutorialUI.displayMessage("Student is not in any tutorial group.");
            return;
        }
        currentGroup.removeStudent(studentToMove);

        // Add the student to the new tutorial group
        newGroup.addStudent(studentToMove);

        tutorialUI.displayMessage("Student moved to the new tutorial group.");
    }

    public void findStudentInTutorialGroup() {
        // Get the tutorial group code from the user
        String tutorialCode = tutorialUI.inputTutorialGroupCode();

        // Find the tutorial group by code
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialCode);

        if (tutorialGroup != null) {
            // Check if the tutorial group has any students
            if (tutorialGroup.getStudentCount() > 0) {
                // Prompt the user to enter the student ID
                String studentID = tutorialUI.inputStudentID();

                // Check if the student exists in the tutorial group
                Student student = tutorialGroup.findStudentByID(studentID);

                if (student != null) {
                    // Remove the student from the tutorial group
                    tutorialUI.displayStudentDetails(student);
                } else {
                    tutorialUI.displayMessage("Student not found in the tutorial group.");
                }
            } else {
                tutorialUI.displayMessage("No students in the tutorial group.");
            }
        } else {
            tutorialUI.displayMessage("Tutorial group not found.");
        }
    }

    public void listAllStudentsInTutorialGroup() {
        // Get the tutorial group code from the user
        String tutorialCode = tutorialUI.inputTutorialGroupCode();

        // Find the tutorial group by code
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialCode);

        if (tutorialGroup != null) {
            String students = tutorialGroup.listAllStudentsByGroup();

            if (!students.isEmpty()) {
                tutorialUI.listAllStudentsInTutorialGroup(students);
            } else {
                tutorialUI.displayMessage("No students in the tutorial group.");
            }
        } else {
            tutorialUI.displayMessage("Tutorial group not found.");
        }
    }

    public void filterTutorialGroupsBasedOnCriteria() {
        int filterChoice = 0;
        do {
            filterChoice = tutorialUI.getFilterChoice();
            switch (filterChoice) {
                case 1 -> {
                    SinglyLinkedList<TutorialGroup> filteredGroup = filterGroupByStudentNum();
                    tutorialUI.listAllTutorialGroups(filteredGroupToString(filteredGroup));
                }
                case 2 -> {
                    int year = tutorialUI.inputYear();
                    SinglyLinkedList<TutorialGroup> filteredGroup = filterGroupByYear(year);
                    tutorialUI.listAllTutorialGroups(filteredGroupToString(filteredGroup));
                }
                case 3 -> {
                    int sem = tutorialUI.inputSem();
                    SinglyLinkedList<TutorialGroup> filteredGroup = filterGroupBySem(sem);
                    tutorialUI.listAllTutorialGroups(filteredGroupToString(filteredGroup));
                }
                case 4 -> {

                }

                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (filterChoice != 4);
    }

    private String filteredGroupToString(SinglyLinkedList<TutorialGroup> filteredTutors) {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = filteredTutors.getEntry(i);
            outputStr.append(tutorialGroup.toString()).append("\n");
        }
        return outputStr.toString();
    }

    private SinglyLinkedList<TutorialGroup> filterGroupBySem(int sem) {
        SinglyLinkedList<TutorialGroup> filteredGroup = new SinglyLinkedList<>();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            if (tutorialGroup.getSemester() == sem) {
                filteredGroup.add(tutorialGroup);
            }
        }
        return filteredGroup;
    }

    private SinglyLinkedList<TutorialGroup> filterGroupByYear(int year) {
        SinglyLinkedList<TutorialGroup> filteredGroup = new SinglyLinkedList<>();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            if (tutorialGroup.getYear() == year) {
                filteredGroup.add(tutorialGroup);
            }
        }
        return filteredGroup;
    }

    private SinglyLinkedList<TutorialGroup> filterGroupByStudentNum() {
        SinglyLinkedList<TutorialGroup> filteredGroup = new SinglyLinkedList<>();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            if (tutorialGroup.getStudentCount() >= 3) {
                filteredGroup.add(tutorialGroup);
            }
        }
        return filteredGroup;
    }

    public void generateTutorialGroupReports() {
        tutorialUI.displayMessage("RECENT JOINED STUDENT RECORD (LAST 3)\n");
        // Prompt the user for the tutorial group code
        String tutorialCode = tutorialUI.inputTutorialGroupCode();

        // Find the tutorial group by code
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialCode);

        if (tutorialGroup != null) {
            ArrayStack<Student> studentStack = new ArrayStack<>(20); // Create an ArrayStack to hold the last 3 students

            // Iterate through the students in the tutorial group and push them onto the stack
            SinglyLinkedList<Student> students = tutorialGroup.getStudents();
            if (students != null) {
                for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                    Student student = students.getEntry(i);
                    if (student != null) {
                        studentStack.push(student);
                    }
                }
            }

            // Display the last 5 students (LIFO order)
            tutorialUI.displayMessage("Last 3 students in the tutorial group:");
            int count = 0;
            while (!studentStack.isEmpty() && count < 3) {
                Student student = studentStack.pop();
                if (student != null) {
                    tutorialUI.displayStudentDetails(student);
                    count++;
                }
            }
        } else {
            tutorialUI.displayMessage("Tutorial group not found.");
        }
    }

    public String displayTutGroup() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            outputStr.append(tutorialGroup.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public void addStudentToTutorialGroup() {
        // Prompt the user to create a new tutorial group or select an existing one
        String tutorialCode = tutorialUI.inputTutorialGroupCode();

        // Check if the tutorial group already exists
        TutorialGroup existingGroup = findTutorialGroupByCode(tutorialCode);

        if (existingGroup != null) {
            // Create a new Student object with the entered information
            Student newStudent = tutorialUI.inputStudentDetails();

            // Add the student to the existing tutorial group
            existingGroup.addStudent(newStudent);

            tutorialUI.displayMessage("Student added to the tutorial group.");
        } else {
            // The tutorial group doesn't exist, so you need to create it first
            tutorialUI.displayMessage("Tutorial group does not exist. Please create a new tutorial group first.");
        }
    }

    private TutorialGroup findTutorialGroupByCode(String tutorialCode) {
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            if (tutorialGroup.getTutorialCode().equals(tutorialCode)) {
                return tutorialGroup;
            }
        }
        return null; // Tutorial group not found
    }
}
