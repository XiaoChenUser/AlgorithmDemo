package com.example.sort.utils;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;

import java.util.Arrays;
import java.util.Random;

public class SortUtils {
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void check(Sort sort) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = dataCheck();
            int[] arr2 = new int[10000];
            System.arraycopy(arr, 0, arr2, 0, arr.length);

            Arrays.sort(arr);
            sort.sort(arr2);

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != arr2[j]) {
                    System.out.println("After sort:");
                    System.out.println("arr:" + JSON.toJSONString(arr));
                    System.out.println("arr2:" + JSON.toJSONString(arr2));
                    throw new RuntimeException("Sort error");
                }
            }
            System.out.println(i + " : successful");
        }
    }

    public static void check(Sort sort,int max) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = dataCheck(max);
            int[] arr2 = new int[10000];
            System.arraycopy(arr, 0, arr2, 0, arr.length);

            Arrays.sort(arr);
            sort.sort(arr2);

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != arr2[j]) {
                    System.out.println("After sort:");
                    System.out.println("arr:" + JSON.toJSONString(arr));
                    System.out.println("arr2:" + JSON.toJSONString(arr2));
                    throw new RuntimeException("Sort error");
                }
            }
            System.out.println(i + " : successful");
        }
    }

    private static int[] dataCheck() {
        return dataCheck(10000);
    }

    private static int[] dataCheck(int max) {
        int[] nums = new int[10000];
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums[i] = r.nextInt(max);
        }
        return nums;
    }
}
