package UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Application {
    /*
    岛问题
    【题目】
    一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连，如
    果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛？
    【举例】
    001010
    111010
    100100
    000000
    这个矩阵中有三个岛
    【进阶】
    如何设计一个并行算法解决这个问题
     */

    /**
     * 数组封装后的对象
     */
    private static class Node {
        int i;
        int j;
        int value;

        public Node(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    private static Node[][] nodes;
    private static UnionFind.UnionFindSet<Node> unionFindSet;

    /**
     * 第二种解法
     * 也是递归感染，但是是并行的。将地图进行划分，然后分别统计，最后将结果合并。
     */
    public static int countIslandsUnionFind(int[][] m) throws InterruptedException {
        if (m == null || m[0] == null) {
            return 0;
        }
        // 获取矩阵大小
        int N = m.length;
        int M = m[0].length;
        // 设置返回值数组，供两个线程使用
        final int[] results = {0, 0};
        // 将数组的元素封装成对象，并将岛加入列表，放入并查集
        List<Node> list = new ArrayList<>();
        nodes = new Node[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node node = new Node(i, j, m[i][j]);
                nodes[i][j] = node;
                if (m[i][j] == 1) {
                    list.add(node);
                }
            }
        }
        // 初始化并查集
        unionFindSet = new UnionFind.UnionFindSet<Node>(list);
        // 开启两个线程，分别统计一半
        final CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M / 2; j++) {
                        if (nodes[i][j].value == 1) {
                            results[0]++;
                            infectUnionFind(i, j, 0, N, 0, M / 2);
                        }
                    }
                }
                latch.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    for (int j = M / 2; j < M; j++) {
                        if (nodes[i][j].value == 1) {
                            results[1]++;
                            infectUnionFind(i, j, 0, N, M / 2, M);
                        }
                    }
                }
                latch.countDown();
            }
        });
        t1.start();
        t2.start();
        latch.await();
        // 合并，判断分界线两侧的元素是否是相连的岛
        int result = results[0] + results[1];
        for (int i = 0; i < N; i++) {
            if (nodes[i][M / 2 - 1].value == nodes[i][M / 2].value && nodes[i][M / 2 - 1].value == 2 && !unionFindSet.isSameSet(nodes[i][M / 2 - 1], nodes[i][M / 2])) {
                unionFindSet.union(nodes[i][M / 2 - 1], nodes[i][M / 2]);
                result--;
            }
        }
        return result;
    }

    /**
     * 感染过程
     */
    private static boolean infectUnionFind(int i, int j, int N1, int N2, int M1, int M2) {
        if (i < N1 || i >= N2 || j < M1 || j >= M2 || nodes[i][j].value != 1) {
            return false;
        }
        // i,j没有越界且当前位置为1
        nodes[i][j].value = 2;
        // 感染上下左右四个位置
        if (infectUnionFind(i + 1, j, N1, N2, M1, M2)) {
            unionFindSet.union(nodes[i][j], nodes[i + 1][j]);
        }
        if (infectUnionFind(i - 1, j, N1, N2, M1, M2)) {
            unionFindSet.union(nodes[i][j], nodes[i - 1][j]);
        }
        if (infectUnionFind(i, j + 1, N1, N2, M1, M2)) {
            unionFindSet.union(nodes[i][j], nodes[i][j + 1]);
        }
        if (infectUnionFind(i, j - 1, N1, N2, M1, M2)) {
            unionFindSet.union(nodes[i][j], nodes[i][j - 1]);
        }
        return true;
    }

    /**
     * 第一种解法
     * 递归感染
     * 时间复杂度 O(N*M)
     */
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        // 获取矩阵大小
        int N = m.length;
        int M = m[0].length;
        int result = 0;
        // 遍历矩阵中每个元素
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 是岛则进行感染过程
                if (m[i][j] == 1) {
                    result++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return result;
    }

    /**
     * 递归传染
     */
    private static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        // i,j没有越界且当前位置为1
        m[i][j] = 2;
        // 感染上下左右四个位置
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }
}

