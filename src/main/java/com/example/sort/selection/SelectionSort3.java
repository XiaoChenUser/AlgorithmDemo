package com.example.sort.selection;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

/**
 * 一次取出 2 个值，找到较小的那个和 minPos 比较
 */
public class SelectionSort3 implements Sort {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, -9, 0, 4, 211, -21};
//        System.out.println("Before sort:");
//        System.out.println(JSON.toJSONString(nums));

        SelectionSort3 selectionSort3 = new SelectionSort3();
        selectionSort3.sort(nums);

        SortUtils.check(selectionSort3);
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            int minPos = i;
            for (int j = i+1; j < nums.length-1; j+=2) {
//                System.out.println(nums[j] + " - " + nums[j+1]);
                int smaller = nums[j] < nums[j + 1] ? j : j + 1;
                minPos = nums[smaller]<nums[minPos] ? smaller : minPos;
            }
            minPos = nums[nums.length - 1] < nums[minPos] ? nums.length - 1 : minPos;
            SortUtils.swap(nums, i, minPos);
//            System.out.println("After " + i + ":");
//            System.out.println(JSON.toJSONString(nums));
        }

    }
}
