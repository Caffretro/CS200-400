import java.util.Arrays;

public class ArrayCopies {

    public static void main(String[] args) {
        String[][] original = new String[3][3];
        String[][] copyA = original;
        //most shallow copy
        String[][] copyB = original.clone();
        //second level copy, see pdf, one new object
        String[][] copyD = new String[original.length][];
            for (int i = 0; i < original.length; i++) {
                copyD[i] = new String[original[i].length];
                //creating three new array and then fill in
                for (int j = 0; j < copyD[i].length; j++) {
                    copyD[i][j] = new String(original[i][j]);
                    //fill in
                }
            }
            //Arrays.equals(arg0, arg1);
                //this thing has two cryteria:
                //1. compare length; 
                //2. compare contents in each position
            //Arrays.deepEquals(arg0; arg1);
                //just compare contents;
                //this is the most shallow equal
                //comparing copyA, B, and D with original would both return true
    }

}
