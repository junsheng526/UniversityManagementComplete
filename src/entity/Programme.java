package entity;

/**
 *
 * @author Your Name A
 */
import adt.DoubleLinkedQueue;
import java.io.Serializable;
import java.util.ListIterator;

public class Programme implements Serializable {

    private String programmeCode;
    private String name;
    private DoubleLinkedQueue<TutorialGroup> tutorialGroups = new DoubleLinkedQueue<>();

    public Programme() {
    }

    public Programme(String programmeCode, String name) {
        this.programmeCode = programmeCode;
        this.name = name;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleLinkedQueue<TutorialGroup> getTutorialGroups() {
        return tutorialGroups;
    }

    public void setTutorialGroups(DoubleLinkedQueue<TutorialGroup> tutorialGroups) {
        this.tutorialGroups = tutorialGroups;
    }

    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.enqueue(tutorialGroup);
    }

    public boolean removeTutorialGroupByCode(String tutorialCode) {
        ListIterator<TutorialGroup> iterator = tutorialGroups.getListIterator();
        while (iterator.hasNext()) {
            TutorialGroup tutorialGroup = iterator.next();
            if (tutorialGroup.getTutorialCode().equals(tutorialCode)) {
                int i = tutorialGroups.indexOf(tutorialGroup);
                tutorialGroups.dequeue(i);
                return true; // Tutorial group found and removed
            }
        }

        return false; // Tutorial group not found
    }

    public String listAllTutGroupByProgramme() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= tutorialGroups.size(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.get(i);
            outputStr.append(tutorialGroup.toString()).append("\n");
        }
        return outputStr.toString();
    }

    @Override
    public String toString() {
        return String.format("%-20s %-50s", programmeCode, name);
    }
}
