package com.example.sort.selection;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

/**
 * 一次内层循环将最小值放在 i, 最大值放在 len-i-1
 */
public class SelectionSort2 implements Sort {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, -9, 0, 4, 211};
//        System.out.println("Before sort:");
//        System.out.println(JSON.toJSONString(nums));

        SelectionSort2 selectionSort2 = new SelectionSort2();
        selectionSort2.sort(nums);

        SortUtils.check(selectionSort2);
    }


    @Override
    public void sort(int[] nums) {
        int minPos;
        int maxPos;
        for (int i = 0; i < nums.length-i-1; i++) {
            minPos = i;
            maxPos = nums.length - i - 1;
            for (int j = i; j <= nums.length-i-1; j++) {
                minPos = nums[j] < nums[minPos] ? j : minPos;
                maxPos = nums[j] > nums[maxPos] ? j : maxPos;
            }
            if(minPos==nums.length-i-1){
                if (maxPos == i) {
                    //swap min & max
                    SortUtils.swap(nums, i, nums.length-i-1);
                }else {
                    //swap min first.
                    SortUtils.swap(nums, i, minPos);
                    SortUtils.swap(nums, nums.length-i-1, maxPos);
                }
            }else {
                if (maxPos == i) {
                    //swap max first.
                    SortUtils.swap(nums, nums.length-i-1, maxPos);
                    SortUtils.swap(nums, i, minPos);
                }else {
                    //swap normally
                    SortUtils.swap(nums, i, minPos);
                    SortUtils.swap(nums, nums.length-i-1, maxPos);
                }
            }
//            System.out.println("After " + i + ":");
//            System.out.println(JSON.toJSONString(nums));
        }
    }
}
