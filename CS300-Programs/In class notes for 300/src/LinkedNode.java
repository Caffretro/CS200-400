
public class LinkedNode <T>{
    public T data;
    public LinkedNode<T> next;
    public LinkedNode(T data, LinkedNode<T> next) {
        this.data = data;
        this.next = next;
    }
    
    public static void main(String[] args) {
        LinkedNode<String> list= new LinkedNode<String>(
            "A", new LinkedNode<String>(
            "B", new LinkedNode<String>(
            "C", null
            )));
        System.out.println(list.next.next.data);
        list.next = new LinkedNode("X", list.next.next);
        //replacing "B" and the pointer to C with "X" and the pointer to C
        System.out.println(list.next.data);
    }
}
