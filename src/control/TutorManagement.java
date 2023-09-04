package control;

/**
 *
 * @author Your Name B
 */
import adt.SortedArrayList;
import boundary.TutorManagementUI;
import dao.TutorDAO;
import entity.Tutor;
import utility.MessageUI;

public class TutorManagement {

    private SortedArrayList<Tutor> tutorList = new SortedArrayList<>();
    private final TutorManagementUI tutorUI = new TutorManagementUI();
    private TutorDAO tutorDAO = new TutorDAO();

    int add = 0, remove = 0, edit = 0;

    public TutorManagement() {
        tutorList = tutorDAO.retrieveFromFile();
    }

    public void runTutorManagement() {
        int choice = 0;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 0 ->
                    MessageUI.displayExitMessage();
                case 1 -> {
                    addNewTutor();
                    tutorUI.listAllTutors(getAllTutors());
                    tutorUI.displayMessage("New tutor added.");
                    add++;
                }
                case 2 -> {
                    removeTutor();
                    tutorUI.listAllTutors(getAllTutors());
                    remove++;
                }
                case 3 ->
                    findTutor();
                case 4 -> {
                    amendTutorDetails();
                    tutorUI.listAllTutors(getAllTutors());
                    edit++;
                }
                case 5 ->
                    tutorUI.listAllTutors(getAllTutors());
                case 6 -> {
                    // Filter tutors based on criteria logic here
                }
                case 7 ->
                    generateReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewTutor() {
        Tutor newTutor = tutorUI.inputTutorDetails();
        tutorList.add(newTutor);
        tutorDAO.saveToFile(tutorList);
    }

    public void removeTutor() {
        String tutorId = tutorUI.inputTutorId();
        Tutor tutor = findTutorById(tutorId);
        if (tutor != null) {
            tutorList.remove(tutor);
            tutorDAO.saveToFile(tutorList);
            tutorUI.displayMessage("Tutor removed.");
        } else {
            tutorUI.displayMessage("Tutor not found.");
        }
    }

    public void findTutor() {
        String tutorId = tutorUI.inputTutorId();
        Tutor tutor = findTutorById(tutorId);
        if (tutor != null) {
            tutorUI.displayTutorDetails(tutor);
        } else {
            tutorUI.displayMessage("Tutor not found.");
        }
    }

    public void amendTutorDetails() {
        String tutorId = tutorUI.inputTutorId();
        Tutor tutor = findTutorById(tutorId);
        if (tutor != null) {
            tutorUI.displayMessage("### Please enter new details ###");
            Tutor newTutorDetails = tutorUI.inputTutorDetails();
            tutorList.replace(tutor, newTutorDetails);
            tutorDAO.saveToFile(tutorList);
            tutorUI.displayMessage("Tutor details amended.");
        } else {
            tutorUI.displayMessage("Tutor not found.");
        }
    }

    public void generateReports() {
        tutorUI.listAllTutors(getAllTutors());
        int totalTutors = tutorList.totalNumberOfObject();
        tutorUI.generateTutorReports(totalTutors, add, remove, edit);
    }

    private Tutor findTutorById(String tutorId) {
        for (int i = 0; i < tutorList.totalNumberOfObject(); i++) {
            Tutor tutor = tutorList.getObject(i);
            if (tutor.getTutorInfo().getTutorId().equals(tutorId)) {
                return tutor;
            }
        }
        return null;
    }

    private String getAllTutors() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < tutorList.totalNumberOfObject(); i++) {
            Tutor tutor = tutorList.getObject(i);
            outputStr.append(tutor.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public static void main(String[] args) {
        TutorManagement tutorManagement = new TutorManagement();
        tutorManagement.runTutorManagement();
    }
}

