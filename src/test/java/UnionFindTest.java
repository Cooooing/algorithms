import UnionFind.Application;
import org.junit.Test;

public class UnionFindTest {
    @Test
    public void countIslandsTest() throws InterruptedException {
        int[][] m1 = new int[1000][1000];
        int[][] m2 = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                int temp = (int) (Math.random() * 2);
                m1[i][j] = temp;
                m2[i][j] = m1[i][j];
            }
        }
        System.out.println("递归感染过程（单线程）：" + Application.countIslands(m1));
        System.out.println("划分地图，多线程并行：" + Application.countIslandsUnionFind(m2));

    }
}