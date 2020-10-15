import java.util.Scanner;
public class Lec07 {
    private Scanner in;
    public Lec07 (Scanner in) {this.in = in;}
    
    public int readNumber() {
        System.out.print("please enter a number: ");
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        else {
            in.nextLine();
            return readNumber();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Lec07 haha = new Lec07(in);
        System.out.println("My number is bigger: " + (haha.readNumber() + 1));
        

    }

}
