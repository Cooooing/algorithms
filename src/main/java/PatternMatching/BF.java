package PatternMatching;

public class BF {
    /**
     * 暴力算法（Brute Force）
     */
    public static int BF(String S, String P) {
        char[] s = S.toCharArray();
        char[] p = P.toCharArray();
        int i = 0;
        int j = 0;
        // 遍历主串所有元素，从每个元素作为起点和模式串比较
        while (i < s.length && j < p.length) {
            if (s[i] == p[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == p.length) {
            return i - j;
        }
        return -1;
    }
}
