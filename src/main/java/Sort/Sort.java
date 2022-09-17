package Sort;

import java.util.Arrays;

public class Sort {
    /*
    综合排序
    将不同排序的优势结合在一起
     */

    /*
    排序算法的稳定性
    同样值的个体之间，如果不因为排序而改变相对次序，就是这个排序是有稳定性的；否则就没有。

    不具备稳定性的排序：
    选择排序、快速排序、堆排序、希尔排序

    具备稳定性的排序：
    冒泡排序、插入排序、归并排序、一切桶排序思想下的排序

    目前没有找到时间复杂度 0(n1ogn) ，额外空间复杂度0(1)，又稳定的排序。
    基于比较的排序，时间复杂度至少 O(nlogn)
    稳定的排序，空间复杂度至少 O(n)
     */

    /*
    基数排序与计数排序、桶排序这三种排序算法都利用了桶的概念，但对桶的使用方式不同
    基数排序：根据键值的每位数字来分配桶；
    计数排序：每个桶只存储单一键值；
    桶排序：每个桶存储一定范围的数值；
     */

    /**
     * 桶排序（计数排序的升级版）
     * 利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。为了使桶排序更加高效，我们需要做到这两点：
     * 在额外空间充足的情况下，尽量增大桶的数量
     * 使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
     * 时间复杂度 O(n+k)
     * 空间复杂度 O(n*k)
     */
    public static int[] bucketSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 获取最大、最小值
        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (min > value) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }
        // 桶的数量
        int bucketSize = 5;
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][0];
        // 利用函数映射关系将数据分配到各个桶中
        for (int j : arr) {
            int index = (j - min) / bucketSize;
            buckets[index] = arrayAppend(buckets[index], j);
        }
        // 对每个桶进行排序
        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 使用了冒泡排序
            bucket = bubbleSort(bucket);
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }
        return arr;
    }

    /**
     * 计数排序
     * 找出待排序的数组中最大和最小的元素
     * 统计数组中每个值为i的元素出现的次数，存入数组C的第i项
     * 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
     * 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
     * 时间复杂度 O(n+k)
     * 空间复杂度 O(k)
     * 空间换时间
     */
    public static int[] countingSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 获取最大、最小值
        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (min > value) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }
        // 处理负数的情况
        int difference = 0;
        if (min < 0) {
            difference = -min;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += difference;
        }
        // 排序
        int[] bucket = new int[max + difference + 1];
        for (int value : arr) {
            bucket[value]++;
        }
        int socketIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                arr[socketIndex++] = i - difference;
                bucket[i]--;
            }
        }
        return arr;
    }

    /**
     * 基数排序
     * 一种非比较型整数排序算法
     * 其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
     * 时间复杂度 O(k*n)
     * 空间复杂度 O(k+n)
     */
    public static int[] radixSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        // 获取最大值
        int max = arr[0];
        for (int j : arr) {
            if (max < j) {
                max = j;
            }
        }
        // 获取最高位数
        int maxDigit = 0;
        if (max == 0) {
            maxDigit = 1;
        } else {
            for (int i = max; i != 0; i /= 10) {
                maxDigit++;
            }
        }
        // 排序
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];
            for (int k : arr) {
                int bucket = ((k % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], k);
            }
            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }
        return arr;
    }

    /**
     * 自动扩容，并保存数据
     */
    private static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * 堆排序
     * 创建一个堆 H[0……n-1]；
     * 把堆首（最大值）和堆尾互换；
     * 把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
     * 重复步骤 2，直到堆的尺寸为 1。
     * 时间复杂度 O(nlogn)
     */
    public static int[] heapSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int len = arr.length;
        /* 创建大根堆
        从最后一个父节点（即len/2的位置）开始进行 heapify 过程
         */
        for (int i = len / 2; i >= 0; i--) {
            heapify(arr, i, len);
        }
        /* 排序
        将最大的根与堆尾交换，同时堆尺寸减一，即排好最大的
        然后再重新与子节点比较，将大的值换到根
         */
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    /**
     * 使得一个数组是堆有序的，即根节点的值大于（小于）左右子节点的值
     */
    private static void heapify(int[] arr, int i, int len) {
        // 左节点
        int left = 2 * i + 1;
        // 右节点
        int right = 2 * i + 2;
        // 父节点
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    /**
     * 快速排序
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * 类似荷兰国旗问题
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(logn)
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
     * 递归调用划分函数进行排序（优化）
     * 优化了等于基准的部分，使得每次排序时，一次可以排所有等于基准的元素。比之前每次只排基准好一些。
     * 但也只是针对有重复元素的排序
     */
    private static int[] quickSortProcessOptimization(int[] arr, int l, int r) {
        if (l < r) {
            int[] partitionIndex = partitionOptimization(arr, l, r);
            partitionOptimization(arr, l, partitionIndex[0]);
            partitionOptimization(arr, partitionIndex[1], r);
        }
        return arr;
    }

    /**
     * 选取基准值，进行划分（优化）
     */
    private static int[] partitionOptimization(int[] arr, int l, int r) {
        // 选取基准值
        int pivot = l;
        r++;
        int i = l + 1;
        while (i < r) {
            if (arr[i] < arr[pivot]) {
                // 小于时，交换左边界+1的元素，左边界l+1，判断下一个元素（交换过来元素都已经过判断）
                swap(arr, ++l, i++);
            } else if (arr[i] > arr[pivot]) {
                // 大于时，交换有边界-1的元素，右边界r-1，判断原位置，因为交换后的元素未经过判断
                swap(arr, --r, i);
            } else {
                // 相等时，什么都不做，判断下一个元素
                i++;
            }
        }
        swap(arr, pivot, l--);
        return new int[]{l, r};
    }


    /**
     * 归并排序
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     * 重复步骤 3 直到某一指针达到序列尾；
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
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
     * 希尔排序（插入排序的改进）
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
     * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
     * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序。
     * 时间复杂度 O(nlog2n)
     */
    public static int[] shellSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int temp;
        // 每次增量为数组长度的一半，以后每次减半
        for (int step = arr.length / 2; step >= 1; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
        return arr;
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
