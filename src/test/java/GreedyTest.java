import Greedy.Question_1;
import Greedy.Question_2;
import Greedy.Question_3;
import org.junit.Test;

/**
 * 贪心算法题目测试类
 */
public class GreedyTest {

    @Test
    public void question_3(){
        System.out.println(Question_3.num1(14));
        System.out.println(Question_3.num2(14));
    }

    @Test
    public void question_2(){
        int[] p = random();
        int[] c = random();
        System.out.println(Question_2.findMaximizedCapital(c.length,50,p,c));
    }

    @Test
    public void question_1(){
        int[] arr = random();
        System.out.println(Question_1.lessMoney(arr));
    }

    public static int[] random(){
        int[] a = new int[1000];
        for (int i = 0; i < 1000; i++) {
            a[i] = (int) (1000 * Math.random());
        }
        return a;
    }
}
