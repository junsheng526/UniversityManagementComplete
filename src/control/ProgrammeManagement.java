package control;

/**
 *
 * @author Your Name C
 */
import adt.DoubleLinkedQueue;
import entity.Programme;
import boundary.ProgrammeManagementUI;
import dao.ProgrammeDAO;
import entity.TutorialGroup;
import java.util.ListIterator;

public class ProgrammeManagement {

    private DoubleLinkedQueue<Programme> programmeQueue = new DoubleLinkedQueue<>();
    private final ProgrammeManagementUI programmeUI = new ProgrammeManagementUI();
    private ProgrammeDAO programmeDAO = new ProgrammeDAO();

    private int added = 0, removed = 0, amended = 0;

//    public DoubleLinkedQueue<Programme> initializeProgrammeQueue() {
//        programmeQueue.enqueue(new Programme("RSW", "Software Engineer"));
//        programmeQueue.enqueue(new Programme("RSD", "Data Science"));
//        programmeQueue.enqueue(new Programme("RAC", "Accounting"));
//        return programmeQueue;
//    }
    public ProgrammeManagement() {
        programmeQueue = programmeDAO.retrieveFromFile();
    }

    public void runProgrammeManagement() {
//        DoubleLinkedQueue<Programme> programmeQueue = initializeProgrammeQueue();
        int choice;
        do {
            choice = programmeUI.getMenuChoice();
            switch (choice) {
                case 0 -> // Save programmes to a file or perform cleanup before exiting.
                    programmeUI.displayMessage("Exiting Programme Management.");
                case 1 ->
                    addProgramme();
                case 2 ->
                    removeProgramme();
                case 3 ->
                    findProgramme();
                case 4 ->
                    amendProgrammeDetails();
                case 5 ->
                    listAllProgrammes();
                case 6 ->
                    addTutorialGroupToProgramme();
                case 7 ->
                    removeTutorialGroupFromProgramme();
                case 8 ->
                    listAllTutorialGroupsForProgramme();
                case 9 ->
                    generateProgrammeReports();
                default ->
                    programmeUI.displayMessage("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
    }

    public void addProgramme() {
        Programme newProgramme = programmeUI.inputProgrammeDetails();
        programmeQueue.enqueue(newProgramme);
        programmeDAO.saveToFile(programmeQueue);
        added++;
        programmeUI.displayMessage("Programme added successfully.");
    }

    public void removeProgramme() {
        String programmeCode = programmeUI.inputProgrammeCode();
        Programme programme = findProgrammeByCode(programmeCode);
        if (programme != null) {
            int position = findProgrammePosition(programme);
            programmeQueue.dequeue(position);
            programmeDAO.saveToFile(programmeQueue);
            removed++;
            programmeUI.displayMessage("Programme removed successfully.");
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public int findProgrammePosition(Programme targetProgramme) {
        ListIterator<Programme> iterator = programmeQueue.getListIterator();
        int position = 1;

        while (iterator.hasNext()) {
            Programme currentProgramme = iterator.next();
            if (currentProgramme.equals(targetProgramme)) {
                return position;
            }
            position++;
        }

        return -1; // Return -1 if not found
    }

    public void findProgramme() {
        String programmeCode = programmeUI.inputProgrammeCode();
        Programme programme = findProgrammeByCode(programmeCode);
        if (programme != null) {
            programmeUI.displayProgrammeDetails(programme);
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void amendProgrammeDetails() {
        String programmeCode = programmeUI.inputProgrammeCode();
        Programme programme = findProgrammeByCode(programmeCode);
        if (programme != null) {
            int position = findProgrammePosition(programme);
            programmeUI.displayMessage("### Please enter new details ###");
            Programme newProgrammeDetails = programmeUI.inputProgrammeDetails();
            programmeQueue.set(position, newProgrammeDetails);
            programmeDAO.saveToFile(programmeQueue);
            amended++;
            programmeUI.displayMessage("Programme details amended successfully.");
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void listAllProgrammes() {
        ListIterator<Programme> iterator = programmeQueue.getListIterator();
        programmeUI.listAllProgrammesHeader(); // Add a method to display the header

        while (iterator.hasNext()) {
            Programme programme = iterator.next();
            programmeUI.displayProgramme(programme);
        }

        programmeUI.listAllProgrammesFooter(); // Add a method to display the footer
    }

    public void addTutorialGroupToProgramme() {

        // Prompt the user to specify the program code
        String programmeCode = programmeUI.inputProgrammeCode();

        // Find the program in the queue
        Programme programme = findProgrammeByCode(programmeCode);

        if (programme != null) {
            // Collect tutorial group details from the user
            TutorialGroup newTutorialGroup = programmeUI.inputTutorialGroupDetails();
            // Add the tutorial group to the program
            programme.addTutorialGroup(newTutorialGroup);

            // Update the program in the queue
            int position = findProgrammePosition(programme);
            programmeQueue.set(position, programme);
            programmeDAO.saveToFile(programmeQueue);

            // Display a confirmation message
            programmeUI.displayMessage("Tutorial group added to the program successfully.");
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void removeTutorialGroupFromProgramme() {
        // Prompt the user for the programme code
        String programmeCode = programmeUI.inputProgrammeCode();

        // Find the programme by its code
        Programme currentProgramme = findProgrammeByCode(programmeCode);

        if (currentProgramme != null) {
            // Prompt the user for the tutorial group code to remove
            String tutorialCode = programmeUI.inputTutorialGroupCode();

            // Remove the tutorial group by tutorialCode
            boolean removed = currentProgramme.removeTutorialGroupByCode(tutorialCode);
            programmeDAO.saveToFile(programmeQueue);

            if (removed) {
                programmeUI.displayMessage("Tutorial group removed successfully.");
            } else {
                programmeUI.displayMessage("Tutorial group not found.");
            }
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void listAllTutorialGroupsForProgramme() {
        String programmeCode = programmeUI.inputProgrammeCode();
        // Find the program in the queue
        Programme programme = findProgrammeByCode(programmeCode);

        if (programme != null) {
            String tutorialGroups = programme.listAllTutGroupByProgramme();
            if (tutorialGroups.isEmpty()) {
                programmeUI.displayMessage("No tutorial groups found for this program.");
            } else {
                programmeUI.listAllTutGroupByProgramme(tutorialGroups);
            }
        } else {
            programmeUI.displayMessage("Programme not found.");
        }
    }

    public void generateProgrammeReports() {
        int totalProgrammes = programmeQueue.size();
        programmeUI.generateProgrammeReports(totalProgrammes, added, removed, amended);
    }

    private Programme findProgrammeByCode(String programmeCode) {
        ListIterator<Programme> iterator = programmeQueue.getListIterator();

        while (iterator.hasNext()) {
            Programme programme = iterator.next();
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }
}
