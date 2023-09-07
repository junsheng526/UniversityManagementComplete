package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Your Name A, B, C, D
 */
public class UniversityManagementUI {

    private final Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("University Management System");
        printLine(35);
        System.out.println("1. Course Management");
        System.out.println("2. Programme Management");
        System.out.println("3. Tutor Management");
        System.out.println("4. Tutorial Group Management");
        System.out.println("0. Exit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 4) {
                System.out.println("Invalid choice. Please enter a number between 0 and 4.");
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
}
