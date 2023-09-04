package boundary;

/**
 *
 * @author Your Name B
 */
import entity.Student;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TutorialGroupManagementUI {

    private final Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("TUTORIAL GROUP MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add Student to Tutorial Group");
        System.out.println("2. Remove Student from Tutorial Group");
        System.out.println("3. Change Tutorial Group for Student");
        System.out.println("4. Find Student in Tutorial Group");
        System.out.println("5. List All Students in Tutorial Group");
        System.out.println("6. Filter Tutorial Groups Based on Criteria");
        System.out.println("7. Generate Tutorial Group Reports");
        System.out.println("0. Quit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 7) {
                System.out.println("Invalid choice. Please enter a number between 0 and 7.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println();

        return choice;
    }

    public void listAllTutorialGroups(String outputStr) {
        printLine(130);
        System.out.printf("%-20s %-5s %-5s %-15s %-20s\n", "Tutorial Group", "Year", "Semester", "Date Added", "Number of Students");
        printLine(130);
        System.out.print(outputStr);
        printLine(130);
    }

    public void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    public void listAllStudentsInTutorialGroup(String outputStr) {
        printLine(130);
        System.out.printf("%-15s %-50s\n", "Student ID", "Student Name");
        printLine(130);
        System.out.print(outputStr);
        printLine(130);
    }

    public String inputStudentID() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        return studentID;
    }

    public Student inputStudentDetails() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        return new Student(studentID, name);
    }

    public String inputTutorialGroupCode() {
        System.out.print("Enter tutorial group code: ");
        String code = scanner.nextLine();
        return code;
    }

    public String inputNewTutorialGroupCode() {
        System.out.print("Enter new tutorial group code: ");
        String newCode = scanner.nextLine();
        return newCode;
    }

    public void displayStudentDetails(Student student) {
        System.out.println("Student Details");
        System.out.println("Student ID: " + student.getStudentID());
        System.out.println("Student Name: " + student.getName());
        // Add other student details as needed
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void generateTutorialGroupReports(int totalStudents, int added, int removed, int changed) {
        clearScreen();

        System.out.println("=== Tutorial Group Management Report ===");
        System.out.println("Total Number of Students                  >> " + totalStudents);
        System.out.println("Total Number of Students Added            >> " + added);
        System.out.println("Total Number of Students Removed          >> " + removed);
        System.out.println("Total Number of Students Tutorial Changed >> " + changed);
        System.out.println("=======================================");

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
