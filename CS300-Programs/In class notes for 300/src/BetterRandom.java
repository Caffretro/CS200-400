import java.util.Random;

public class BetterRandom extends Random{
    
    public BetterRandom(long seed) {super();}
    
    @Override
    public int nextInt() {
        return this.nextInt(Integer.MAX_VALUE) + 1;
    }
    /*private Random random;
    
    public BetterRandom() {random = new Random();}
    public BetterRandom(long seed) {random = new Random(seed);}
    
    public int nextInt() {
        return random.nextInt();
    }*/
    public static void main(String[] args) {
        BetterRandom randGen = new BetterRandom();
        System.out.println(randGen.nextInt());

    }
    

}
