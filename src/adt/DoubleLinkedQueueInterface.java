package adt;

/**
 *
 * @author Your Name C
 */
import java.util.ListIterator;

public interface DoubleLinkedQueueInterface<T> {

    public boolean enqueue(T newEntry);

    public boolean dequeue();

    public boolean dequeue(int position);

    public T peek();

    public boolean isEmpty();

    public void clear();

    public int size();

    public T get(int position);

    public boolean contains(T element);

    public int indexOf(T element);

    public boolean set(int position, T newEntry);

    @Override
    public String toString();

    public ListIterator<T> getListIterator(int position);

    public ListIterator<T> getListIterator();
}
