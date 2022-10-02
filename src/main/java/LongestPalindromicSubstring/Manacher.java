package LongestPalindromicSubstring;

public class Manacher {
    public static int Manacher(String str) {
        int len = str.length() * 2 + 1;
        char[] string = new char[len];
        char[] str1 = str.toCharArray();
        int index = 0;
        // 将字符串中添加特殊字符，让字符串只有奇回文
        for (int i = 0; i < len; i++) {
            string[i] = (i % 2) == 0 ? '#' : str1[index++];
        }
        // 记录回文半径的数组
        int[] p = new int[len];
        // r最右回文右边界，c对应的最左回文中心，maxn最大回文半径
        int r = -1;
        int c = -1;
        int maxn = Integer.MIN_VALUE;
        // 从左往右遍历
        for (int i = 0; i < len; i++) {
            // i>r 时，回文半径为1，否则回文半径就是 i对应i‘的回文半径 或者 i到r的距离
            p[i] = r > i ? Math.min(r - i, p[2 * c - i]) : 1;
            while (i + p[i] < len && i - p[i] > -1) {
                if (string[i + p[i]] == string[i - p[i]]) {
                    p[i]++;
                } else {
                    break;
                }
            }
            // 判断r和c是否可以更新
            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
            // 更新最大回文半径
            maxn = Math.max(maxn, p[i]);
        }
        return maxn - 1;
    }
}
