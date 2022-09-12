import java.util.Arrays;

public class Sort {

    /**
     * 快速排序
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * 类似荷兰国旗问题
     * 时间复杂度 O(n^2)
     * 但它的平摊期望时间是O(nlongn)，而且隐含的常数因子很小，比归并小很多。所以对绝大多数顺序性较弱的随机数列来说，快排优于归并
     */
    public static int[] quickSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        return quickSortProcess(arr, 0, arr.length - 1);
    }

    /**
     * 递归调用划分函数进行排序
     */
    private static int[] quickSortProcess(int[] arr, int l, int r) {
        if (l < r) {
            int partitionIndex = partition(arr, l, r);
            quickSortProcess(arr, l, partitionIndex - 1);
            quickSortProcess(arr, partitionIndex + 1, r);
        }
        return arr;
    }

    /**
     * 选取基准值，进行划分
     */
    private static int partition(int[] arr, int l, int r) {
        // 选取基准值
        int pivot = l;
        int index = pivot + 1;
        for (int i = index; i <= r; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }


    /**
     * 归并排序
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     * 重复步骤 3 直到某一指针达到序列尾；
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     * 时间复杂度 O(nlogn)
     * 额外空间复杂度 O(n)
     */
    public static int[] mergeSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 临时数组，用于存放排序后的数组
        int[] tempArray = new int[array.length];
        merge(arr, tempArray, 0, array.length - 1);
        return arr;
    }

    private static void merge(int[] array, int[] tempArray, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 2);
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        merge(array, tempArray, start1, end1);
        merge(array, tempArray, start2, end2);
        int temp = start;
        // 比较两个数组元素，将较小的放到合并空间。直至其中一个数组遍历结束
        while (start1 <= end1 && start2 <= end2) {
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
    }


    /**
     * 插入排序
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     * 时间复杂度 O(n^2)
     */
    public static int[] insertionSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 从下标为1的元素开始选择插入位置，下标为0只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 从右往左比较，左边的元素比右边大时，交换
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 时间复杂度 O(n^2)
     */
    public static int[] bubbleSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length - 1; i++) {
            // （优化）设定一个标记，为true表示此次循环没有交换，即已排序完成
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     * 时间复杂度 O(n^2)
     */
    public static int[] selectionSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 总共经过n-1次比较
        for (int i = 0; i < arr.length - 1; i++) {
            // 选定i下标的值作为比较的基准
            int temp = i;
            // 在i~n-1上找最小值的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[temp]) {
                    temp = j;
                }
            }
            // 将最小值与i上元素交换
            if (i != temp) {
                swap(arr, i, temp);
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

    /**
     * 交换数组中两元素值
     */
    private static void swap_1(int[] array, int i, int j) {
        /*
        异或运算（无进位相加）
        0^N=N N^N=0
        满足交换律和结合律

        必须保证a，b内存不一致，否则a，b都会为0
        a=甲^乙           b=乙
        a=甲^乙           b=甲^乙^乙=甲^0=甲
        a=甲^乙^甲=乙^0=乙 b=甲
         */
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
