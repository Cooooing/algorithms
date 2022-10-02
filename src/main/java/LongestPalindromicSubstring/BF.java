package LongestPalindromicSubstring;

public class BF {
    public static int BF(String str) {
        char[] string = str.toCharArray();
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            // 判断奇数长度回文
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < str.length() && string[l] == string[r]) {
                l--;
                r++;
            }
            result = Math.max(result, r - l - 1);
            // 判断偶数长度回文
            l = i;
            r = i + 1;
            while (l >= 0 && r < str.length() && string[l] == string[r]) {
                l--;
                r++;
            }
            result = Math.max(result, r - l - 1);
        }
        return result;
    }
}
