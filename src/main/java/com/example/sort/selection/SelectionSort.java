package com.example.sort.selection;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import static com.example.sort.utils.SortUtils.swap;

public class SelectionSort implements Sort {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, -9, 0, 4, 211};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(nums);
        System.out.println(JSON.toJSONString(nums));

        SortUtils.check(selectionSort);
    }

    @Override
    public  void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            t = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[t]) {
                    t = j;
                }
            }
            swap(nums, i, t);
        }
    }


}
