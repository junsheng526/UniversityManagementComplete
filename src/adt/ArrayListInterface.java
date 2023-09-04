package adt;

/**
 *
 * @author Your Name A
 */

import java.util.Iterator;

public interface ArrayListInterface<T> {

    public boolean add(T newEntry);

    public void clear();

    public void clear(int startPosition);

    public boolean contains(T anEntry);

    public ArrayList<T> copy();

    public T getEntry(int givenPosition);

    public int getNumberOfEntries();

    public int getPositionOf(T anEntry);

    public boolean isEmpty();

    public boolean isFull();

    public T remove(int givenPosition);

    public boolean remove(T anEntry);

    public boolean replace(int givenPosition, T newEntry);

    public Iterator<T> getIterator();

}
