
class Jewelry {
    public int getPrice() { return 50;}
    public String getDis() { return ", " + this.getPrice();}
}
public class Ring extends Jewelry {
    public int getPrice() { return 100;}
    public static void main(String[] args) {
        Jewelry j = (Jewelry) new Object();
        Ring ring = new Ring();
        Jewelry j = ring;
        System.out.print(j.getPrice() + j.getDis());
    }
}
