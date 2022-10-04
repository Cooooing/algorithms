package Leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
public class two_sum_1 {
    /**
     * 暴力求解
     */
    public int[] twoSum_1(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    /**
     * 哈希表
     */
    public int[] twoSum_2(int[] nums, int target) {
        int[] res = new int[2];
        // 哈希表 key：值 value：下标
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                res[0] = i;
                res[1] = hashMap.get(nums[i]);
            }
            hashMap.put(target - nums[i], i);
        }
        return res;
    }
}
