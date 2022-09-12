import expand.Expend;
import org.junit.Test;

import java.util.Arrays;

public class Test01 {
    @Test
    public void test() {
        int[] a = new int[]{1,3,4,5,2};
//        System.out.println(Arrays.toString(Expend.partition(a,3)));
        System.out.println(Arrays.toString(Sort.quickSort(a)));
    }

}
