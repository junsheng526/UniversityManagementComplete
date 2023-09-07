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
                    int filterChoice = 0;
                    do {
                        filterChoice = tutorUI.getFilterChoice();
                        switch (filterChoice) {
                            case 1 -> {
                                String facultyFilter = tutorUI.inputFilterByFaculty();
                                SortedArrayList<Tutor> filteredTutors = filterTutorsByFaculty(tutorList, facultyFilter);
                                tutorUI.listAllTutors(filteredTutorsToString(filteredTutors));
                            }
                            case 2 ->{
                                String nameFilter = tutorUI.inputFilterByName();
                                SortedArrayList<Tutor> filteredTutors = filterTutorsByName(tutorList, nameFilter);
                                tutorUI.listAllTutors(filteredTutorsToString(filteredTutors));
                            }
                            case 3 ->{
                                String statusFilter = tutorUI.inputFilterByStatus();
                                SortedArrayList<Tutor> filteredTutors = filterTutorsByStatus(tutorList, statusFilter);
                                tutorUI.listAllTutors(filteredTutorsToString(filteredTutors));
                            }
                            default ->
                                MessageUI.displayInvalidChoiceMessage();
                        }
                    } while (filterChoice != 4);
                }
                case 7 ->
                    generateReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    private String filteredTutorsToString(SortedArrayList<Tutor> filteredTutors) {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < filteredTutors.totalNumberOfObject(); i++) {
            Tutor tutor = filteredTutors.getObject(i);
            outputStr.append(tutor.toString()).append("\n");
        }
        return outputStr.toString();
    }

    // Use equal to filter faculty, need full text but not case sensitive
    private SortedArrayList<Tutor> filterTutorsByFaculty(SortedArrayList<Tutor> tutorList, String facultyFilter) {
        SortedArrayList<Tutor> filteredTutors = new SortedArrayList<>();
        for (int i = 0; i < tutorList.totalNumberOfObject(); i++) {
            Tutor tutor = tutorList.getObject(i);
            if (tutor.getTutorInfo().getFaculty().equalsIgnoreCase(facultyFilter)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;
    }

    // Use contains to find name, no need type full name to filter
    private SortedArrayList<Tutor> filterTutorsByName(SortedArrayList<Tutor> tutorList, String nameFilter) {
        SortedArrayList<Tutor> filteredTutors = new SortedArrayList<>();
        for (int i = 0; i < tutorList.totalNumberOfObject(); i++) {
            Tutor tutor = tutorList.getObject(i);
            if (tutor.getTutorInfo().getName().contains(nameFilter)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;
    }

    // Use equal to filter status, need full text but not case sensitive
    private SortedArrayList<Tutor> filterTutorsByStatus(SortedArrayList<Tutor> tutorList, String statusFilter) {
        SortedArrayList<Tutor> filteredTutors = new SortedArrayList<>();
        for (int i = 0; i < tutorList.totalNumberOfObject(); i++) {
            Tutor tutor = tutorList.getObject(i);
            if (tutor.getTutorInfo().getStatus().equalsIgnoreCase(statusFilter)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;
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
}
