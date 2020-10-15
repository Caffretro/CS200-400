
public class Lec08x {

    public static void main(String[] args) {
        //String and Integer are pre-implemented with a compareTo() method
        Integer[][] array = new Integer[3][2];
        array[1] = new Integer[3];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = i + j;
            }
        }
        //AMAZINGLY, this part only creates 4 new Integer Objects!
        //Since we didn't use "New", java uses 0, 1, 2, 3 several times!
        /*for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }*/
        moreExamples();
    }
    public static void moreExamples() {
        CSstudent a = new CSstudent("a");
        student b = new CSstudent("b");
        student c = new student("c");
        
        b.study();//runtime checks that b is a CSstudent, thus use the CSstudent's study();
        //c.coding(); this doesn't compile, since c is student, not CSstudent
        c.study();
        
    }

}

class CSstudent extends student{
    public CSstudent(String name) { super(name);}
    public void coding() {System.out.println("HAHA");}
    public void study() {System.out.println("Still play PUBG");}
}

class student extends Person{
    public student (String name) { super(name);}
    public void study() {System.out.println("Play PUBG");}
}

class Person{
    private static int population;
    private final String NAME;
    private int age;
    public Person (String name) {
        this.NAME = name;
        age = 10;
        population++;
    }
    public String toString() {
        return NAME + " is one of " + population + " people.";
    }
    
}
