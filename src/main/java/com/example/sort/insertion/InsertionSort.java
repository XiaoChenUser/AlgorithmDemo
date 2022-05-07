package com.example.sort.insertion;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

public class InsertionSort implements Sort {
    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
//        int[] nums = {111, 3, 5, -9, 0, 4, 2};
//        insertionSort.sort(nums);
//        System.out.println(JSON.toJSONString(nums));
        SortUtils.check(insertionSort);
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j;
            //不满足 nums[j]>temp 的，index < j 的不用再比较
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                nums[j + 1] = nums[j];
            }
            //这里包含了 nums[0]>temp 的情况
            nums[j+1] = temp;
        }
    }
}
