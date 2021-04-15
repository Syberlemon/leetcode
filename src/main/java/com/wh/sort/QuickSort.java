package com.wh.sort;

import java.util.Arrays;

/**
 * Created on 2021/4/12
 * 回头把优化也写一下，1基数不动 2随机选基础 3相同元素较多
 * @author wanghao1
 */

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {5,2,1,8,9,3,7,0,4,6};
        Arrays.sort(arr);
//        sort(arr, 0, arr.length - 1);
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
    }

    private static void sort(int[] arr, int left, int right){
        int i = left;
        int j = right;
        if(left < right){
            while(left < right){
                while(left < right && arr[right] >= arr[left]){
                    right--;
                }
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                while(left < right && arr[left] <= arr[right]){
                    left++;
                }

                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            sort(arr, i, left-1);//递归左边
            sort(arr, left +1, j);//递归右边

        }
    }
}
