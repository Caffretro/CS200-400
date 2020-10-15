import java.util.Iterator;

// Since the type T in ADT extends Comparable<T>, but our class doesn't has the limitation, so we
// claim the same thing for our class' generic type T
public class UnsortedArray<T extends Comparable<T>> implements PriorityQueueADT<T>, Iterable<T> {
    // private T bad = new T();
    // private T[] alsoBad = new T[10];

    // private static T bad;
    // can potentially have different Types, but static field is shared

    // private T t;
    // This can work, the type will be passed each time

    private T[] notBad = (T[]) new Comparable[10];
    // Notice, we may have to also change Object to Comparable since we had new limitation on T
    private int size = 0;
    

    @Override
    public void enqueue(T newObject) {
        if (size == notBad.length) {
            // maybe an option to throw exception, or create your shadow array, or copy into a
            // bigger array
        }
        this.notBad[size] = newObject;
        size++;
    }

    @Override
    public T dequeue() {
        // if isEmpty() throw exception
        int bestIndex = 0;
        for (int i = 0; i < size; i++) {
            if (notBad[bestIndex].compareTo(notBad[i]) < 0)
                bestIndex = i;
        }
        T value = notBad[bestIndex];
        notBad[bestIndex] = notBad[size - 1];
        notBad[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public T peek() {
        // if isEmpty() throw exception
        int bestIndex = 0;
        for (int i = 0; i < size; i++) {
            if (notBad[bestIndex].compareTo(notBad[i]) < 0)
                bestIndex = i;
        }
        return notBad[bestIndex];
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (T t: this) { // has to be an array or iterables
            s += t + " ";
            t = null;// this won't affect the original data since t is a local variable we created
        }
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
