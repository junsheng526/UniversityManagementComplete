//package control;
//
///**
// *
// * @author Your Name B
// */
//import adt.ArrayStack;
//import boundary.TutorialGroupManagementUI;
//import entity.Student;
//import entity.TutorialGroup;
//
//public class TutorialGroupManagement {
//
//    private ArrayStack<TutorialGroup> tutorialGroups = new ArrayStack<>(50);
//    private final TutorialGroupManagementUI tutorialUI = new TutorialGroupManagementUI();
//
//    public void runTutorialGroupManagement() {
//        int choice = 0;
//        do {
//            choice = tutorialUI.getMenuChoice();
//            switch (choice) {
//                case 0:
//                    tutorialUI.displayMessage("Exiting the Tutorial Group Management System.");
//                    break;
//                case 1:
//                    addStudentToTutorialGroup();
//                    break;
//                case 2:
//                    removeStudentFromTutorialGroup();
//                    break;
//                case 3:
//                    changeTutorialGroupForStudent();
//                    break;
//                case 4:
//                    // Find latest 10 students
//                    findStudentInTutorialGroup();
//                    break;
//                case 5:
//                    listAllStudentsInTutorialGroup();
//                    break;
//                case 6:
//                    filterTutorialGroupsBasedOnCriteria();
//                    break;
//                case 7:
//                    generateTutorialGroupReports();
//                    break;
//                default:
//                    tutorialUI.displayMessage("Invalid choice. Please enter a valid option.");
//                    break;
//            }
//        } while (choice != 0);
//    }
//
////    public void addStudentToTutorialGroup() {
////        Student newStudent = tutorialUI.inputStudentDetails();
////
////        // Check if there is an active tutorial group (top of the stack)
////        if (!tutorialGroups.isEmpty()) {
////            // Get the top tutorial group from the stack
////            TutorialGroup currentGroup = tutorialGroups.peek();
////
////            // Add the new student to the current tutorial group
////            currentGroup.addStudent(newStudent);
////
////            tutorialUI.displayMessage("Student added to the current tutorial group.");
////        } else {
////            tutorialUI.displayMessage("No active tutorial group. Please create a tutorial group first.");
////        }
////    }
//
//    public void addStudentToTutorialGroup() {
//        // Prompt the user to create a new tutorial group or select an existing one
//        String tutorialCode = tutorialUI.inputTutorialGroupCode();
//
//        // Check if the tutorial group already exists
//        TutorialGroup existingGroup = findTutorialGroupByCode(tutorialCode);
//
//        if (existingGroup != null) {
//            // The tutorial group exists, so you can add a student to it
//            // Prompt the user for student information
//            String studentId = tutorialUI.inputStudentID();
//            String studentName = tutorialUI.inputStudentName();
//
//            // Create a new Student object with the entered information
//            Student newStudent = new Student(studentId, studentName);
//
//            // Add the student to the existing tutorial group
//            existingGroup.addStudent(newStudent);
//
//            tutorialUI.displayMessage("Student added to the tutorial group.");
//        } else {
//            // The tutorial group doesn't exist, so you need to create it first
//            TutorialGroup newGroup = new TutorialGroup(tutorialCode);
//            tutorialGroups.push(newGroup); // Add the new tutorial group to the stack
//
//            tutorialUI.displayMessage("New tutorial group created.");
//        }
//    }
//
//    public void removeStudentFromTutorialGroup() {
//        // Implement the logic to remove a student from a tutorial group using ArrayStack
//        // You may need to search for the student in the stack and then use pop to remove them
//    }
//
//    public void changeTutorialGroupForStudent() {
//        // Implement the logic to change the tutorial group for a student using ArrayStack
//        // You may need to search for the student in the current group, remove them, and then push them into the new group
//    }
//
//    public void findStudentInTutorialGroup() {
//        // Implement the logic to find a student in a tutorial group using ArrayStack
//        // You may need to search for the student and display their details if found
//    }
//
//    public void listAllStudentsInTutorialGroup() {
//        // Implement the logic to list all students in a tutorial group using ArrayStack
//        // You can iterate through the stack and display each student's details
//    }
//
//    public void filterTutorialGroupsBasedOnCriteria() {
//        // Implement the logic to filter tutorial groups based on criteria using ArrayStack
//        // You can define your criteria and filter the students accordingly
//    }
//
//    public void generateTutorialGroupReports() {
//        // Implement the logic to generate relevant reports using ArrayStack
//        // You can calculate and display various statistics based on the students in the group
//    }
//
//    public static void main(String[] args) {
//        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
//        tutorialGroupManagement.runTutorialGroupManagement();
//    }
//}
