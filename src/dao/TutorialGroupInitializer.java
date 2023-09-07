package dao;

/**
 *
 * @author 
 */
import adt.SinglyLinkedList;
import adt.ListInterface;
import entity.TutorialGroup;

public class TutorialGroupInitializer {

    public ListInterface<TutorialGroup> initializeTutorialGroups() {
        ListInterface<TutorialGroup> tutorialGroupList = new SinglyLinkedList<>(); // Initialize your SinglyLinkedList
        tutorialGroupList.add(new TutorialGroup("TUT101", 3, 1, 1));
        tutorialGroupList.add(new TutorialGroup("TUT102", 2, 1, 2));
        tutorialGroupList.add(new TutorialGroup("TUT103", 1, 2, 1));
        // Add more tutorial groups here

        return tutorialGroupList;
    }

    public static void main(String[] args) {
        TutorialGroupInitializer t = new TutorialGroupInitializer();
        ListInterface<TutorialGroup> tutorialGroupList = t.initializeTutorialGroups();
        System.out.println("\nTutorial Groups:\n");
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(i);
            System.out.println(tutorialGroup);
        }
    }
}
