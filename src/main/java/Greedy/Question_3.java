package Greedy;

/**
 * N皇后问题是指在NN的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。
 * 给定一个整数n,返回n皇后的摆法有多少种。
 * n=1,返回1。
 * n=2或3,2皇后和3皇后问题无论怎么摆都不行，返回0。
 * n=8,返回92。
 */
public class Question_3 {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n]; // 第i行皇后，放在了第几列。单数组解决
        return process1(0, record, n);
    }

    /**
     * record[0...i-1]的皇后一定不共行，不共列，不共斜线
     * 目前来到第i行
     * record[0...i-1]表示之前的行，放了皇后的位置
     * n代表整体一共有多少行
     * 返回值为摆完所有皇后一共有多少种合理的摆法
     */
    public static int process1(int i, int[] record, int n) {
        // 终止行
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 当前行在i行，尝试i行所有的列j
        for (int j = 0; j < n; j++) {
            // 当前i行的皇后，放在j列，检查是否冲突（共行，共列，或共斜线）
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    /**
     * 检查record[0...i-1]，record[i..]不需要
     * 返回i行皇后放在j列，是否可以
     */
    public static boolean isValid(int[] record, int i, int j) {
        // 检查前i行皇后
        for (int k = 0; k < i; k++) {
            // 是否列冲突，是否斜行冲突（行减列的绝对值是否相等）
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /*
    常数优化（使用位运算）
     */

    // 最好不要超过32皇后问题，因为int是32位（可以换成long）
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // limit上所有皇后都填满了
        if (colLim == limit) {
            return 1;
        }
        // 所有可以放皇后的位置（候选皇后），为1，不能为0。位信息
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne;
        int res = 0;
        // 遍历所有候选皇后
        while (pos != 0) {
            // 提取最右侧的1，即最右侧可以放皇后的位置
            mostRightOne = pos & (~pos + 1);
            // 候选皇后去除
            pos = pos - mostRightOne;
            // 列限制，斜行限制（>>>无符号右移）
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >> 1);
        }
        return res;
    }
}
