package net;

import java.util.Random;

public class BFPRT {//线性排序算法
    public static void quickSort(int[] arr, int left, int right) {//快速排序，比x大的在x右边，比x小的在x左边
        if (left < right) {
            int i = left, j = right, x = arr[right];//x作为基数
            while (i < j) {
                while (i < j && arr[i] < x)//遇到第一个  大于等于x的数跳出循环
                    i++;
                if (i < j)
                    arr[j--] = arr[i];    //把刚刚第一个大于等于x的数赋给x所指的对象，j指针左移

                while (i < j && arr[j] >= x)//y向左移动，直到遇到第一个比x小的数，停止循环
                    j--;
                if (i < j)
                    arr[i++] = arr[j];
            }
            arr[i] = x;//i指向基数
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
    public static void swap(int[] arr, int i, int j) {//此函数作用在于交换i j下标的值
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int median(int[] arr, int left, int right) {//第一步的划分和第二步的寻找中位数写成一个方法median
        int inx = left;
        /*

         */
        for (int i = left; i <= right - 4; i = i + 5) {//
            quickSort(arr, i, i + 4);
            swap(arr, inx, i + 2);
            inx++;
        }
        int end = (inx - left) * 5 + left;
        if (end < right) {//剩余几个数据
            quickSort(arr, end, right);
            swap(arr, inx, (right + end) / 2);
        }
        return inx;//返回中位数的数量
    }

    public static void selectMe(int[] arr, int left, int right) {
        int inx = right;
        if ((right - left) > 0) {
            inx = median(arr, left, right);
            selectMe(arr, left, inx);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int inx = left;
        if (left < right) {
            int i = left, j = right, x = arr[left];
            while (i < j) {
                while (i < j && arr[j] > x)
                    j--;
                if (i < j)
                    arr[i++] = arr[j];

                while (i < j && arr[i] <= x)
                    i++;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            inx = i;
        }
        return inx;
    }

    public static int select(int[] arr, int left, int right, int key) {
        selectMe(arr, left, right);//首位即为中位数的中位数
        int k = partition(arr, left, right);//k代表位置中位数的中位数所在的位置
        if (k > key) {
            return select(arr, left, k - 1, key);//缩小排序圈,
        } else if (k < key) {
            return select(arr, k + 1, right, key);
        }
        return arr[k];
    }

    public static int BFPRT(int[] arr, int key) {//入口函数
        key -= 1;
        return select(arr, 0, arr.length - 1, key);
    }

    public static void main(String[] args) {
        int[] nums = new int[10];
        Random random = new Random();
        System.out.print("搜索的数组：");
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(50);
        }
        for (int i : nums) {
            System.out.print(i + " ");
        }
        int index = BFPRT(nums, 5);
        System.out.println();
        System.out.print("第10小的元素为：");
        System.out.println(index);
        System.out.print("划分后的数组：");
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
