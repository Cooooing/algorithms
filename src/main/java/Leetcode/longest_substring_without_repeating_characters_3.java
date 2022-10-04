package Leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class longest_substring_without_repeating_characters_3 {

    /**
     * 滑动窗口
     */
    public static int lengthOfLongestSubstring_2(String s) {
        int result = 0;
        // 记录字符最后出现的位置
        int[] last = new int[128];
        Arrays.fill(last, -1);
        // 窗口的开始位置
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            // 字符ascii码作为下标
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            result = Math.max(result, i - start + 1);
            last[index] = i;
        }
        return result;
    }

    /**
     * 暴力
     */
    public static int lengthOfLongestSubstring_1(String s) {
        int result = 0;
        char[] str = s.toCharArray();
        int temp = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                if (!hashMap.containsKey(str[j])) {
                    hashMap.put(str[j], j);
                    temp++;
                } else {
                    break;
                }
            }
            hashMap.clear();
            result = Math.max(result, temp);
            temp = 0;
        }
        return result;
    }
}
