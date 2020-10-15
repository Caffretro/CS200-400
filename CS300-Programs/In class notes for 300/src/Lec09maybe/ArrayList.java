package Lec09maybe;

public class ArrayList<T> implements ListADT<T> {
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[10];
        this.size = 0;
    }

    @Override
    public void add(T newObject) {// O(1) in linked list, O(N) in ArrayList
        if (size == array.length) {// while the original array is fully possessed
            T[] biggerArray = (T[]) new Object[array.length + 1];
            for (int i = 0; i < size; i++) {
                biggerArray[i] = array[i];
            }
            array = biggerArray;
        }
        // while the original array is not fully possessed or we have already created room for new
        // one
        array[size] = newObject;
        size++;
        // TODO Auto-generated method stub

    }

    @Override
    public boolean contains(T findObject) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void add(int index, T newObject) {
        // TODO Auto-generated method stub

    }

    @Override
    public int indexOf(T findObject) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T remove(int index) { // O(N)
        T ret = array[index];
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return ret;
    }

}
