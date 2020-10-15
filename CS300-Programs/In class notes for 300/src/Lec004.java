import java.util.ArrayList;

public class Lec004 {

    public static void main(String[] args) {
        /*Integer z = 5;//auto-boxing
            System.out.println(z.intValue());
            System.out.println(z.toString());
            int anotherZ = z;//auto-unboxing*/
        Integer z = 5;
        ArrayList<Integer> p = new ArrayList<Integer>();
        p.add(6);
        Integer z2 = z;
        ArrayList<Integer> p2 = p;
        
        z2 = z2 + 1;
        p2.set(0, p2.get(0) + 1);
        System.out.println(z + " " + p.get(0));

    }

}
