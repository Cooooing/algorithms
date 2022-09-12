package expand;

import java.util.Arrays;


public class Expend {

    /**
     * 荷兰国旗问题
     * 给定一个整数数组，给定一个值K，这个值在原数组中一定存在
     * 要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，等于K的元素放到数组的中间
     */
    public static int[] partition(int[] array, int key) {
        int[] arr = Arrays.copyOf(array, array.length);
        int l = -1, r = arr.length, i = 0;
        while (i < r) {
            if (arr[i] < key) {
                // 小于时，交换左边界+1的元素，左边界l+1，判断下一个元素（交换过来元素都已经过判断）
                swap(arr, ++l, i++);
            } else if (arr[i] > key) {
                // 大于时，交换有边界-1的元素，右边界r-1，判断原位置，因为交换后的元素未经过判断
                swap(arr, --r, i);
            } else {
                // 相等时，什么都不做，判断下一个元素
                i++;
            }
        }
        return arr;
    }

    /**
     * 交换数组中两元素值
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*
     * 归并排序拓展
     */

    /**
     * 求 逆序对问题
     * 在一个数组中，每一个数右边比当前数小的数，与这个数组成一个逆序对。
     * 如数组 1，3，4，2，5
     * 逆序对为 3,2 4,2
     * 即求 右边有多少个数比当前数小
     */
    public static int reverse(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int[] tempArray = new int[arr.length];
        return mergeReverse(arr, tempArray, 0, arr.length - 1);
    }

    private static int mergeReverse(int[] array, int[] tempArray, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + ((end - start) >> 2);
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        int result = 0;
        int temp = start;
        result += mergeReverse(array, tempArray, start1, end1);
        result += mergeReverse(array, tempArray, start2, end2);
        // 比较两个数组元素，将较小的放到合并空间。直至其中一个数组遍历结束
        while (start1 <= end1 && start2 <= end2) {
            result += array[start1] <= array[start2] ? 0 : (end2 - start2 + 1);
            tempArray[temp++] = array[start1] > array[start2] ? array[start1++] : array[start2++];
        }
        // 将剩余元素添加至合并空间末尾
        while (start1 <= end1) {
            tempArray[temp++] = array[start1++];
        }
        while (start2 <= end2) {
            tempArray[temp++] = array[start2++];
        }
        // 拷贝合并空间内排序结束的数组至原数组
        for (temp = start; temp <= end; temp++) {
            array[temp] = tempArray[temp];
        }
        System.out.println(Arrays.toString(tempArray));
        return result;
    }

    /**
     * 求 小和问题
     * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
     * 如数组 1，3，4，2，5
     * 小和为 1 + 1+3 + 1 + 1+3+4+2 = 16
     * 即求 右边有多少个数比当前数大
     */
    public static int smallSum(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int[] tempArray = new int[arr.length];
        return mergeSmallSum(arr, tempArray, 0, arr.length - 1);
    }

    private static int mergeSmallSum(int[] array, int[] tempArray, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + ((end - start) >> 2);
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        int result = 0;
        int temp = start;
        result += mergeSmallSum(array, tempArray, start1, end1);
        result += mergeSmallSum(array, tempArray, start2, end2);
        // 比较两个数组元素，将较小的放到合并空间。直至其中一个数组遍历结束
        while (start1 <= end1 && start2 <= end2) {
            result += array[start1] < array[start2] ? (end2 - start2 + 1) * array[start1] : 0;
            tempArray[temp++] = array[start1] < array[start2] ? array[start1++] : array[start2++];
        }
        // 将剩余元素添加至合并空间末尾
        while (start1 <= end1) {
            tempArray[temp++] = array[start1++];
        }
        while (start2 <= end2) {
            tempArray[temp++] = array[start2++];
        }
        // 拷贝合并空间内排序结束的数组至原数组
        for (temp = start; temp <= end; temp++) {
            array[temp] = tempArray[temp];
        }
        return result;
    }
}
