package Lec16;

public class Search {

    public static void main(String[] args) {
        int[] list = new int[] {13, 17, 28, 32, 33, 47, 51, 54, 75, 89, 96, 98};
        System.out.println(contains(list, 33));
        System.out.println(contains(list, 54));
        System.out.println(contains(list, 5));
        System.out.println(contains(list, 102));
        System.out.println(contains(list, 30));

    }
    public static boolean contains (int[] list, int element) {
        return contains(list, element, 0);
    }
    private static boolean contains (int[] array, int value, int index) {
        if (index >= array.length) return false;
        if (index < 0) return false;
        if (array[index] == value) return true;
        return contains(array, value, index + 1);
    }
}
