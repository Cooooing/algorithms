import PatternMatching.BF;
import PatternMatching.KMP;
import org.junit.Test;

import java.util.Arrays;

public class PatternMatchingTest {

    @Test
    public void KMPTest() {
        char[] a1 = new char[1000];
        for (int i = 0; i < 1000; i++) {
            a1[i] = (char) (Math.random() * 62);
        }
        int start = (int) (Math.random() * 1000);
        int end = (int) (Math.random() * (1000 - start) + start);
        String a = Arrays.toString(a1);
        String b = a.substring(start, end);
        System.out.println("起始位置：" + start);
        System.out.println("结束位置：" + end);
        System.out.println("子串长度：" + (end - start + 1));

        System.out.println("BF:" + BF.BF(a, b));
        System.out.println("KMP:" + KMP.KMP(a, b));
    }
}
