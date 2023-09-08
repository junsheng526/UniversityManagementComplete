package boundary;

/**
 *
 * @author Your Name C
 */
import entity.Tutor;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TutorManagementUI {

    private final Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("TUTOR MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add New Tutor");
        System.out.println("2. Remove Tutor");
        System.out.println("3. Find Tutor");
        System.out.println("4. Amend Tutor Details");
        System.out.println("5. List All Tutors");
        System.out.println("6. Filter Tutors Based on Criteria");
        System.out.println("7. Generate Tutor Reports");
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

    public int getFilterChoice() {
        int filterOption;

        System.out.println("\n");
        printLine(35);
        System.out.println("FILTER TUTORS MENU");
        printLine(35);
        System.out.println("1. Filter by Faculty");
        System.out.println("2. Filter by Name");
        System.out.println("3. Filter by Status");
        System.out.println("4. Back to main menu");
        printLine(35);
        System.out.print("Please enter your filter choice: ");
        filterOption = -1;
        try {
            filterOption = scanner.nextInt();
            if (filterOption < 1 || filterOption > 4) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        return filterOption;
    }

    public void listAllTutors(String outputStr) {
        printLine(136);
        System.out.printf("%-20s %-15s %-40s %-15s %-15s %-15s %-15s\n", "Tutor ID", "Faculty", "Name", "Phone Number", "Gender", "Status", "Join Date");
        printLine(136);
        System.out.print(outputStr);
        printLine(136);
    }

    public String inputTutorId() {
        System.out.print("Enter tutor ID: ");
        String tutorId = scanner.nextLine();
        return tutorId;
    }

    public Tutor inputTutorDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter tutor ID: ");
        String tutorId = scanner.nextLine();

        System.out.print("Enter faculty: ");
        String faculty = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number (e.g., 012-3456789): ");
            phoneNumber = scanner.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number format. Please enter a valid phone number.");
            }
        }

        String gender;
        while (true) {
            System.out.print("Enter gender (M/F): ");
            gender = scanner.nextLine().toUpperCase();
            if (gender.equals("M") || gender.equals("F")) {
                break;
            } else {
                System.out.println("Invalid gender. Please enter 'M' for Male or 'F' for Female.");
            }
        }

        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        return new Tutor(tutorId, faculty, name, phoneNumber, gender, status);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Phone number should match the pattern "012-3456789"
        return phoneNumber.matches("\\d{3}-\\d{7}");
    }

    public void displayTutorDetails(Tutor tutor) {
        System.out.println("Tutor Details");
        System.out.println("Tutor ID: " + tutor.getTutorInfo().getTutorId());
        System.out.println("Faculty: " + tutor.getTutorInfo().getFaculty());
        System.out.println("Name: " + tutor.getTutorInfo().getName());
        System.out.println("Phone Number: " + tutor.getTutorInfo().getPhoneNumber());
        System.out.println("Join Date: " + tutor.getDateAdded());
    }

    public String inputNewTutorId() {
        System.out.print("Enter new tutor ID: ");
        String newTutorId = scanner.nextLine();
        return newTutorId;
    }

    public String inputFilterByFaculty() {
        String faculty;
        do {
            System.out.print("Enter faculty: ");
            faculty = scanner.nextLine().trim();
            if (faculty.isEmpty()) {
                System.out.println("Faculty cannot be empty. Please enter a valid faculty.");
            }
        } while (faculty.isEmpty());
        return faculty;
    }

    public String inputFilterByName() {
        String name;
        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        } while (name.isEmpty());
        return name;
    }

    public String inputFilterByStatus() {
        String status;
        do {
            System.out.print("Enter status: ");
            status = scanner.nextLine().trim();
            if (status.isEmpty()) {
                System.out.println("Status cannot be empty. Please enter a valid status.");
            }
        } while (status.isEmpty());
        return status;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void generateTutorReports(int totalTutors, int added, int removed, int amended) {
        clearScreen();

        System.out.println("=== Tutor Management Report ===");
        System.out.println("Total Number of Tutors                   >> " + totalTutors);
        System.out.println("Total Number of Tutors Added             >> " + added);
        System.out.println("Total Number of Tutors Removed           >> " + removed);
        System.out.println("Total Number of Tutors Details Amended   >> " + amended);
        System.out.println("==============================");

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

    private void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
