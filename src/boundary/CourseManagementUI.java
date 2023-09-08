package boundary;

/**
 *
 * @author Your Name D
 */
import entity.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("COURSE MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Find Course");
        System.out.println("4. Amend Course Details");
        System.out.println("5. List All Courses");
        System.out.println("6. Add Programme to Course");
        System.out.println("7. Remove Programme from Course");
        System.out.println("8. List All Programmes By Course");
        System.out.println("9. Generate Report");
        System.out.println("0. Quit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 8) {
                System.out.println("Invalid choice. Please enter a number between 0 and 8.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println();

        return choice;
    }

    public void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    public void listAllCourses(String outputStr) {
        printLine(130);
        System.out.printf("%-10s %-15s %-40s %-15s %-15s %-15s %-15s\n", "Course Code", "Category", "Name", "Credit Hours", "Status", "Date Added", "Programmes");
        printLine(130);
        System.out.print(outputStr);
        printLine(130);
    }

    public void listAllProgrammesByCourse(String outputStr) {
        printLine(70);
        System.out.printf("%-20s %-50s\n", "Programme Code", "Name");
        printLine(70);
        System.out.print(outputStr);
        printLine(70);
    }

    public Course inputCourseDetails() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g., BACS1234, BAIT0987): ");
            courseCode = scanner.nextLine().trim();
            if (isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }

        System.out.print("Enter course category: ");
        String category = scanner.nextLine();

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        int creditHours = -1; // Initialize to a default value
        while (creditHours < 0) {
            try {
                System.out.print("Enter course credit hours: ");
                creditHours = Integer.parseInt(scanner.nextLine());
                if (creditHours < 0) {
                    System.out.println("Credit hours must be a non-negative integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        System.out.print("Enter course status: ");
        String status = scanner.nextLine();

        return new Course(courseCode, category, name, creditHours, status);
    }

    private boolean isValidCourseCode(String code) {
        return code.matches("B[A-Z]{3}\\d{4}");
    }

    public Programme inputProgrammeDetails() {
        String programmeCode = inputProgrammeCode();
        System.out.print("Enter programme name: ");
        String name = scanner.nextLine();

        return new Programme(programmeCode, name);
    }

    public String inputProgrammeCode() {
        while (true) {
            System.out.print("Enter programme code (e.g., RST, RIS): ");
            String code = scanner.nextLine().trim();
            if (code.matches("R[A-Z]{2}")) {
                return code;
            } else {
                System.out.println("Invalid format. Please enter a valid programme code.");
            }
        }
    }

    public String inputCourseCode() {
        while (true) {
            System.out.print("Enter course code (e.g., BACS1234, BAIT0987): ");
            String code = scanner.nextLine().trim();
            if (code.matches("B[A-Z]{3}\\d{4}")) {
                return code;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
    }

    public void displayCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course code: " + course.getCourseInfo().getCourseCode());
        System.out.println("Course name: " + course.getCourseInfo().getName());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayCourseManagementReport(int totalCourses, int add, int remove, int edit) {
        clearScreen();

        System.out.println("=== Course Management Report ===");
        System.out.println("Total Number of Courses             >> " + totalCourses);
        System.out.println("Total Number of Programmes Added    >> " + add);
        System.out.println("Total Number of Programmes Deleted  >> " + remove);
        System.out.println("Total Number of Programmes Edited   >> " + edit);
        System.out.println("================================");

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
