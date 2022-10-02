package PatternMatching;

public class KMP {

    public static int KMP(String S, String P) {
        char[] s = S.toCharArray();
        char[] p = P.toCharArray();
        int i = 0;
        int j = 0;
        int[] next = getNext(P);
        while (i < s.length && j < p.length) {
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == p.length ? i - j : -1;
    }

    public static int[] getNext(String P) {
        char[] p = P.toCharArray();
        int[] next = new int[P.length()];
        // 起始位置为-1
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            // p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[k] == p[j]) {
                next[++j] = ++k;
            } else {
                // 不匹配，前缀则回到上一个最大重复的位置（next数组构造本身就用到了next数组的特性）
                k = next[k];
            }
        }
        return next;
    }
}
