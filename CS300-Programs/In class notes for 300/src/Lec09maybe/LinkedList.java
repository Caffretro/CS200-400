package Lec09maybe;

public class LinkedList<T> implements ListADT<T>{
    //Little Hint: every class T has their own .equals() method
    private LinkedNode<T> head;
    //default no-arg constructor sets all reference fields to null
    
    
    @Override
    public void add(T newObject) {//adds to the end of the list
        LinkedNode<T> lastNode = head;
        if (head = null) {
            head = new LinkedNode<>(newObject, null);
        }
        else {
          linkedNote<T> lastNode = head;
        }
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = new LinkedNode<>(newObject, null);
        }
    }

    @Override
    public boolean contains(T findObject) {
        LinkedNode<T> checkNode = head;
        while (checkNode != null) {
            if (checkNode.data.equals(findObject)) {
                return true;
            }
            checkNode = checkNode.next;
            
        }
        return false;
    }

    @Override
    public int size() {
        int counter = 0;
        LinkedNode<T> checkNode = head;
        while (checkNode != null) {
            checkNode = checkNode.next;
            counter++;
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(int index, T newObject) {
        //see lecture notes
    }

    @Override
    public int indexOf(T findObject) {
        int steps = 0;
        LinkedNode<T> checkNode = head;
        while (checkNode != null) {
            if (checkNode.data.equals(findObject)) {
                return steps;
            }
            checkNode = checkNode.next;
            steps++;
        }
        return -1;//throw exception
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }
    
}
