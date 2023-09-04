package adt;

/**
 *
 * @author Your Name A
 */

import java.io.Serializable;
import java.util.Iterator;

public class ArrayList<T> implements ArrayListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity(numberOfEntries + 1);
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public void clear(int startPosition) {
        checkPosition(startPosition);
        for (int i = startPosition - 1; i < numberOfEntries; i++) {
            array[i] = null;
        }
        numberOfEntries = startPosition - 1;
    }

    @Override
    public boolean contains(T anEntry) {
        return getPositionOf(anEntry) >= 1;
    }

    @Override
    public ArrayList<T> copy() {
        ArrayList<T> copiedArray = new ArrayList<>();
        for (int i = 0; i < numberOfEntries; i++) {
            copiedArray.add(array[i]);
        }
        return copiedArray;
    }

    @Override
    public T getEntry(int givenPosition) {
        checkPosition(givenPosition);
        return array[givenPosition - 1];
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public int getPositionOf(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(array[i])) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public T remove(int givenPosition) {
        checkPosition(givenPosition);
        T removedEntry = array[givenPosition - 1];
        removeGap(givenPosition);
        numberOfEntries--;
        return removedEntry;
    }

    @Override
    public boolean remove(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(anEntry)) {
                removeGap(i + 1);
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; index++) {
            outputStr += array[index] + "\n";
        }
        return outputStr;
    }

    @Override
    public Iterator<T> getIterator() {
        return new IteratorForArrayList();
    }

//  utility method
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int oldSize = array.length;
            T[] newArray = (T[]) new Object[oldSize * 2];
            for (int i = 0; i < oldSize; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;
        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    private void checkPosition(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            System.out.println("Invalid Position");
        }
    }

    private class IteratorForArrayList implements Iterator<T> {

        private int index;

        public IteratorForArrayList() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < numberOfEntries;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T element = (T) array[index++];
                return element;
            } else {
                return null;
            }
        }
    }

}
