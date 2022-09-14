import org.junit.Test;

import java.util.Arrays;

public class SortTest {
    /*
    对数器（通过用大量测试数据来验证算法是否正确的一种方式）：
    1.有一个你想要测的方法a；
    2.实现一个绝对正确但是复杂度不好的方法b；
    3.实现一个随机样本产生器；
    4.实现对比算法a和b的方法；
    5.把方法a和方法b比对多次来验证方法a是否正确；
    6.如果有一个样本使得比对出错，打印样本分析是哪个方法出错；
    7.当样本数量很多时比对测试依然正确，可以确定方法a已经正确。
     */
    @Test
    public void sortTest() {
        int[] a = new int[1000];

        for (int i = 0; i < 1000; i++) {
            a[i] = (int) (-1000 * Math.random() + 500);
        }
        System.out.println(Arrays.toString(Sort.insertionSort(a)));
        System.out.println(Arrays.toString(Sort.bubbleSort(a)));
        System.out.println(Arrays.toString(Sort.selectionSort(a)));
        System.out.println(Arrays.toString(Sort.mergeSort(a)));
        System.out.println(Arrays.toString(Sort.quickSort(a)));
        System.out.println(Arrays.toString(Sort.heapSort(a)));
        System.out.println(Arrays.toString(Sort.radixSort(a)));
        System.out.println(Arrays.toString(Sort.countingSort(a)));
        System.out.println(Arrays.toString(Sort.bucketSort(a)));
        System.out.println(Arrays.toString(Sort.shellSort(a)));

        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}