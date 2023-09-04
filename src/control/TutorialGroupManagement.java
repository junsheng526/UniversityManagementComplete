package control;

/**
 *
 * @author Your Name B
 */
import adt.SortedArrayList;
import boundary.TutorialGroupManagementUI;
import dao.TutorialGroupDAO;
import entity.TutorialGroup;
import utility.MessageUI;

public class TutorialGroupManagement {

    private SortedArrayList<TutorialGroup> tutorialGroupList = new SortedArrayList<>();
    private final TutorialGroupManagementUI tutorialGroupUI = new TutorialGroupManagementUI();
    private TutorialGroupDAO tutorialGroupDAO = new TutorialGroupDAO();

    int add = 0, remove = 0, edit = 0;

    public TutorialGroupManagement() {
        tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
    }

    public void runTutorialGroupManagement() {
        int choice = 0;
        do {
            choice = tutorialGroupUI.getMenuChoice();
            switch (choice) {
                case 0 ->
                    MessageUI.displayExitMessage();
                case 1 -> {
                    addNewTutorialGroup();
                    tutorialGroupUI.listAllTutorialGroups(getAllTutorialGroups());
                    tutorialGroupUI.displayMessage("New tutorial group added.");
                    add++;
                }
                case 2 -> {
                    removeTutorialGroup();
                    tutorialGroupUI.listAllTutorialGroups(getAllTutorialGroups());
                    remove++;
                }
                case 3 ->
                    findTutorialGroup();
                case 4 -> {
                    amendTutorialGroupDetails();
                    tutorialGroupUI.listAllTutorialGroups(getAllTutorialGroups());
                    edit++;
                }
                case 5 ->
                    tutorialGroupUI.listAllTutorialGroups(getAllTutorialGroups());
                case 6 -> {
                    // Add student to tutorial group
                    addStudentToTutorialGroup();
                }
                case 7 -> {
                    // Remove student from tutorial group
                    removeStudentFromTutorialGroup();
                }
                case 8 -> {
                    // List all students in tutorial group
                    listAllStudentsInTutorialGroup();
                }
                case 9 ->
                    generateReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewTutorialGroup() {
        TutorialGroup newTutorialGroup = tutorialGroupUI.inputTutorialGroupDetails();
        tutorialGroupList.add(newTutorialGroup);
        tutorialGroupDAO.saveToFile(tutorialGroupList);
    }

    public void removeTutorialGroup() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            tutorialGroupList.remove(tutorialGroup);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            tutorialGroupUI.displayMessage("Tutorial group removed.");
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void findTutorialGroup() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            tutorialGroupUI.displayTutorialGroupDetails(tutorialGroup);
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void amendTutorialGroupDetails() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            tutorialGroupUI.displayMessage("### Please enter new details ###");
            TutorialGroup newTutorialGroupDetails = tutorialGroupUI.inputTutorialGroupDetails();
            tutorialGroupList.replace(tutorialGroup, newTutorialGroupDetails);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            tutorialGroupUI.displayMessage("Tutorial group details amended.");
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void addStudentToTutorialGroup() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            // Add student logic here
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void removeStudentFromTutorialGroup() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            // Remove student logic here
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void listAllStudentsInTutorialGroup() {
        String tutorialGroupCode = tutorialGroupUI.inputTutorialGroupCode();
        TutorialGroup tutorialGroup = findTutorialGroupByCode(tutorialGroupCode);
        if (tutorialGroup != null) {
            // List students in tutorial group logic here
        } else {
            tutorialGroupUI.displayMessage("Tutorial group not found.");
        }
    }

    public void generateReports() {
        tutorialGroupUI.listAllTutorialGroups(getAllTutorialGroups());
        int totalTutorialGroups = tutorialGroupList.totalNumberOfObject();
        tutorialGroupUI.generateTutorialGroupReports(totalTutorialGroups, add, remove, edit);
    }

    private TutorialGroup findTutorialGroupByCode(String tutorialGroupCode) {
        for (int i = 0; i < tutorialGroupList.totalNumberOfObject(); i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getObject(i);
            if (tutorialGroup.getTutorialGroupInfo().getTutorialGroupCode().equals(tutorialGroupCode)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    private String getAllTutorialGroups() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < tutorialGroupList.totalNumberOfObject(); i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getObject(i);
            outputStr.append(tutorialGroup.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public static void main(String[] args) {
        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
        tutorialGroupManagement.runTutorialGroupManagement();
    }
}

