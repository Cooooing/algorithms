package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正数数组 costs
 * 正数数组 profits
 * 正数k
 * 正数m
 * 含义：
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱（利润）
 * k表示你只能串行的最多做k个项目
 * m表示你初始的资金
 * 说明：
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出：
 * 你最后获得的最大钱数。
 */
public class Question_2 {

    public static class Node {
        /*
        p 利润
        c 成本
         */
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     * 成本比较器
     */
    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    /**
     * 利润比较器
     */
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Node> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        // 将所有项目扔到被锁池中，成本组织的小根堆
        for (int i = 0; i < profits.length; i++) {
            minCostQueue.add(new Node(profits[i], capital[i]));
        }
        // 进行k轮，每次遍历所有的项目，可以解锁的全解锁
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().c <= w) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return w;
            }
            w += maxProfitQueue.poll().p;
        }
        return w;
    }
}
