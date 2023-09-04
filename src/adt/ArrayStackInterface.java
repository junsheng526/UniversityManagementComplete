package adt;

/**
 *
 * @author Your Name B
 */

public interface ArrayStackInterface<T> {

    public T push(T newEntry);

    public T peek();

    public T pop();

    public boolean isEmpty();

    public T get(int position);

    public int getSize();

    public boolean contains(T searchEntry);

    public int indexOf(T searchEntry);

    @Override
    public String toString();
}
