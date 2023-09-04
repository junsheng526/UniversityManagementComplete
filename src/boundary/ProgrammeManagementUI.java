package boundary;

/**
 *
 * @author Your Name A
 */
import entity.Programme;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProgrammeManagementUI {

    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("PROGRAMME MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add New Programme");
        System.out.println("2. Remove Programme");
        System.out.println("3. Find Programme");
        System.out.println("4. Amend Programme Details");
        System.out.println("5. List All Programmes");
        System.out.println("6. Add Tutorial Group to Programme");
        System.out.println("7. Remove Tutorial Group from Programme");
        System.out.println("8. List All Tutorial Groups for Programme");
        System.out.println("9. Generate Programme Reports");
        System.out.println("0. Quit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 9) {
                System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }

        scanner.nextLine(); // Consume the newline left in the buffer
        System.out.println(); // Add a blank line after input

        return choice;
    }

    // Method to input Programme details
    public Programme inputProgrammeDetails() {
        System.out.print("Enter programme code: ");
        String programmeCode = scanner.nextLine();
        System.out.print("Enter programme name: ");
        String name = scanner.nextLine();

        return new Programme(programmeCode, name);
    }

    // Method to input Programme code
    public String inputProgrammeCode() {
        System.out.print("Enter programme code: ");
        String code = scanner.nextLine();
        return code;
    }

    // Method to display Programme details
    public void displayProgrammeDetails(Programme programme) {
        System.out.println("Programme Details");
        System.out.println("Programme code: " + programme.getProgrammeCode());
        System.out.println("Programme name: " + programme.getName());
    }

    // Method to list all Programmes
    public void listAllProgrammes(List<Programme> programmes) {
        printLine(70);
        System.out.printf("%-20s %-50s\n", "Programme Code", "Name");
        printLine(70);
        for (Programme programme : programmes) {
            System.out.printf("%-20s %-50s\n", programme.getProgrammeCode(), programme.getName());
        }
        printLine(70);
    }

    // Method to add a Tutorial Group to a Programme
    public String inputTutorialGroupCode() {
        System.out.print("Enter tutorial group code: ");
        String code = scanner.nextLine();
        return code;
    }

    // Method to remove a Tutorial Group from a Programme
    public void removeTutorialGroupFromProgramme() {
        System.out.print("Enter tutorial group code to remove: ");
        String code = scanner.nextLine();
    }

    // Method to list all Tutorial Groups for a Programme
//    public void listAllTutorialGroups(List<TutorialGroup> tutorialGroups) {
//        printLine(70);
//        System.out.printf("%-20s %-50s\n", "Tutorial Group Code", "Name");
//        printLine(70);
//        for (TutorialGroup tutorialGroup : tutorialGroups) {
//            System.out.printf("%-20s %-50s\n", tutorialGroup.getCode(), tutorialGroup.getName());
//        }
//        printLine(70);
//    }
    // Method to generate Programme reports
    public void generateProgrammeReports(int totalProgrammes, int added, int removed, int amended) {
        System.out.println("=== Programme Management Report ===");
        System.out.println("Total Number of Programmes             >> " + totalProgrammes);
        System.out.println("Total Number of Programmes Added       >> " + added);
        System.out.println("Total Number of Programmes Removed     >> " + removed);
        System.out.println("Total Number of Programmes Amended     >> " + amended);
        System.out.println("=====================================");

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
}
