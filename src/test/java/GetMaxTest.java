import org.junit.Test;

import java.util.Arrays;

public class GetMaxTest {
    @Test
    public void getMaxTest() {
        int[] a = new int[100];

        for (int i = 0; i < 100; i++) {
            a[i] = (int) (100 * Math.random());
        }
        System.out.println(Arrays.toString(a));

        System.out.println(GetMax.getMax(a));
    }
}