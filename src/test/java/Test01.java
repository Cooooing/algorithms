import Sort.Sort;
import org.junit.Test;

import java.util.Arrays;

public class Test01 {
    @Test
    public void test() {

        int[] arr = new int[]{2, 5, 3, 1, 4};
        System.out.println(Arrays.toString(Sort.shellSort(arr)));


    }
}
