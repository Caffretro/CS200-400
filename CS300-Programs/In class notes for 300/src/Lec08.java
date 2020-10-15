
class Lec08 {
    protected int x;
    private int y;//only visible in this class, not even its subclass Child
    public void addNum(int x, int y) {this.x = x; this.y = y;}
    }
class HAHA extends Lec08{
    public HAHA(int z) {
        super.addNum(z, z);
        x = x + 3;
        y = y + 3;//cannot access y
    }
}
public class Main {
    Lec08 a = new HAHA(2);
    System.out.println();

}
