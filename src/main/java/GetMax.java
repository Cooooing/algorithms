public class GetMax {
    public static int getMax(int[] array) {
        return getMaxBisection(array, 0, array.length - 1);
    }

    /**
     * 二分法求数组array[l...r]范围上的最大值
     */
    public static int getMaxBisection(int[] array, int l, int r) {
        if (l == r) {
            return array[l];
        }
        // 求中点，此写法防止越界
        int mid = l + ((r - l) >> 1);
        int leftMax = getMaxBisection(array, l, mid);
        int rightMax = getMaxBisection(array, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }
}
