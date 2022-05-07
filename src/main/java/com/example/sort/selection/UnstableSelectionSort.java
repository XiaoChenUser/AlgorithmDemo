package com.example.sort.selection;

import com.alibaba.fastjson.JSON;
import com.example.sort.utils.SortUtils;

/**
 * 稳定性：数值相同的两个数在排序前后，相对顺序是否会发生变化。
 * 证明 Selection Sort 是不稳定排序。
 */
public class UnstableSelectionSort {
    public static void main(String[] args) {
        int[] nums = {5, 1, 3, 5, 0};
        int[] index = {0, 1, 2, 3, 4};
        sort(nums, index);
        //After sort:
        //[0,1,3,5,5]
        //[4,1,2,3,0]
    }

    public static void sort(int[] nums, int[] index) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minPos]) {
                    minPos = j;
                }
            }
            SortUtils.swap(nums, i, minPos);
            SortUtils.swap(index, i, minPos);
        }
        System.out.println("After sort:");
        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(index));
    }

}
