package Lec09maybe;

public interface ListADT<T> {
    //unordered (subset of java.util.Collection)
    public void add(T newObject);
    public boolean contains(T findObject);
    public int size();
    public boolean isEmpty();
    //Ordered (subset of java.util.List)
    public void add(int index, T newObject);
    public int indexOf(T findObject);
    public T get(int index);
    public T remove(int index);
}
