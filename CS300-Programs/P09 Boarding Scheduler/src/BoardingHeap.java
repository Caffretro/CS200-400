public class BoardingHeap {
    //You may store additional private fields as needed
    private Passenger[] heap; //array of passengers currently in the heap
    private int size;
    //This should be your only constructor
    public BoardingHeap() {
        this.heap = new Passenger[10];
        this.size = 0;
    }
    
    //Public heap methods. You may add private methods
    public void enqueue(Passenger p) { 
        int current = 1;
        if (this.size == 0) {
            heap[1] = p;
            size++;
        }
        else {
            if (this.size < heap.length - 1) {
                heap[size + 1] = p;
                size++;
            }
            else {
                Passenger[] newHeap = new Passenger[heap.length * 2];
                for (int i = 0; i < heap.length; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
                heap[size + 1] = p;
                size++;
            }
            for (int i = this.size; i > 1; i--) {
                if (p.getPreferredBoarding() > heap[current].getPreferredBoarding()) {
                    current = current/2;
                }
                else if (p.getPreferredBoarding() == heap[current].getPreferredBoarding()) {
                    
                }
            }
        }
    }
    public Passenger dequeue() {
        int current = 1;
        if (this.size == 0) {
            return 0;// FIXME
        }
        if (this.size == 1) {
            return heap[1];
            heap[1] = null;
        }
        else {
            Passenger temp = heap[1];
            heap[1] = heap[size];
            heap[size] = null;
            size--;
            sortForDequeue();
        }
        
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void sortForDequeue() {
        Passenger temp = heap[1];
        Passenger left, right;
        
    }
}