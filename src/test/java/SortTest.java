import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] a = new int[100];

        for (int i = 0; i < 100; i++) {
            a[i] = (int) (100 * Math.random());
        }
        System.out.println(GetMax.getMax(a));
    }
}