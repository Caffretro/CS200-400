import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

enum Days {Monday, Tuesday, Wednesday}
public class Enum implements Test{
    
   
    public static void main(String[] args) {
        
        System.out.println(Days.Monday.ordinal()); // 0
        System.out.println(Days.valueOf("Tuesday").ordinal()); // 1
        System.out.println(Days.values()[2]); // Wednesday
        
        
    }
}

class TestStatic{
    static final int FINAL_SCORE = 100;
    static void printFinalScore() { System.out.println(FINAL_SCORE);}
    
    int getScore() {return this.FINAL_SCORE;}
}

//@FunctionalInterface
interface Test {
    static String haha(String str) {
        return "";
    }
}
