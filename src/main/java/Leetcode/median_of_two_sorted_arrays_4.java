package Leetcode;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class median_of_two_sorted_arrays_4 {

    public static void main(String[] args) {
        int[] a1 = new int[]{2};
        int[] a2 = new int[]{};
        System.out.println(findMedianSortedArrays_1(a1, a2));
    }


    /**
     * 两个指针分别向后遍历（按大小），直至到数组长度和的一半。
     */
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        double res = 0;
        int index = (nums1.length + nums2.length) / 2;
        int i = 0, j = 0;
        int v1 = 0, v2 = 0;
        int k = 0;
        while (k <= index && (i < nums1.length || j < nums2.length)) {
            v1 = v2;
            if (i == nums1.length || (i < nums1.length && j < nums2.length && nums1[i] > nums2[j])) {
                v2 = nums2[j++];
            } else {
                v2 = nums1[i++];
            }
            k++;
        }
        res = (nums1.length + nums2.length) % 2 == 1 ? (double) v2 : (v2 + v1) / 2.0;
        return res;
    }
}
