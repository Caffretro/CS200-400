package Lec15;

public class Recursion {
    public static int power(int base, int exponent) {
        int powered;
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        } else {
            powered = base * power(base, exponent - 1);
            return powered;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(power(2, 1));
        trace(3);
    }
    public static void trace(int i) {
        System.out.println(i);
        if (i > 10) return;
        if (i % 2 == 0) trace(i + 3);
        else trace (i * 2);
        System.out.println(i);
    }

    // following is an example that prints out the entries in a linked list
    class LinkedNode {
        public String data;
        public LinkedNode next;

        public LinkedNode(String data, LinkedNode next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() { // this
            if (next == null) {
                return this.data;
            } else {
                return this.data + next.toString();
            }
        }
        
    }

}
