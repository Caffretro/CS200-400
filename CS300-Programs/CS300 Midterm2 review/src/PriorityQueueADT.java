public interface PriorityQueueADT <T extends Comparable<T>> {
    public void enqueue(T newObject);
    public T dequeue(); // returns the element with the highest priority
    public T peek();
    public boolean isEmpty();
}
 
// Implementation Strategy 1: unsorted linked list, search through to return highest priority
// enqueue() - O(1): quickly adds to the beginning or end of the linked list
// dequeue() - O(n): takes more time search through list before returning the highest priority
 
// Implementation Strategy 2: maintain sorted linked list, quickly return highest priority
// enqueue() - O(n): takes time searching for position to insert new data into (in sorted order)
// dequeue() - O(1): quickly returns the highest priority value from the linked lists's head
 
// Implementation Strategy 3: use heap data structure as a compromise between strategies 1 and 2
// enqueue() - O(log(n)): partially sort the data as it is added into the heap
// dequeue() - O(log(n)): do enough work to update the partially sorted heap
 
// The complexity of calling both enqueue() and dequeue() on n objects will then be:
// O(n) for strategies 1 and 2, although a different method is faster in each
// O(log(n)) for strategy 3... which is part of why heaps are a valuable data structure