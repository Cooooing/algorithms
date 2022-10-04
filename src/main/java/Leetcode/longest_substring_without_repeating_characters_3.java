package Leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class longest_substring_without_repeating_characters_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring_1(""));
        System.out.println(lengthOfLongestSubstring_1(" "));
        System.out.println(lengthOfLongestSubstring_1("a"));
        System.out.println(lengthOfLongestSubstring_1("au"));
        System.out.println(lengthOfLongestSubstring_1("abcabcbb"));
        System.out.println(lengthOfLongestSubstring_1("bbbbb"));
        System.out.println(lengthOfLongestSubstring_1("dvdf"));
    }

    /**
     * 暴力
     */
    public static int lengthOfLongestSubstring_1(String s) {
        int result = 0;
        char[] str = s.toCharArray();
        int temp = 0;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            for (int j = i ; j < str.length; j++) {
                if (!hashMap.containsKey(str[j])) {
                    hashMap.put(str[j],j);
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
