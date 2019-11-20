package net;

import java.util.Random;

public class mergeSort {//归并排序   采用递归调用的方式

    public static void mergeSort(int[] arr) {//入口函数
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int L, int R) {//分治策略
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);//*
        sort(arr, L, mid);
        sort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    // 比较左右两部分的元素，哪个小，把那个元素填入temp中
    public static void merge(int[] arr, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];//待排序的数组长度
        int i = 0;
        int p1 = L;//左边数组的初始指针所在位置
        int p2 = mid + 1;//右边数组的初始指针所在位置
        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while (p1 <= mid && p2 <= R) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[i++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

    public static void main(String[] arg) {
        int[] arr = new int[10];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            for (int n = 0; n < 10; n++) {
                arr[n] = new Random().nextInt(100) + 1;
            }
            mergeSort(arr);
        }

        long current = System.currentTimeMillis();

        // printArr(arr);
        System.out.println(current - start);
    }
}
