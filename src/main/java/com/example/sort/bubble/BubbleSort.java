package com.example.sort.bubble;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

/**
 * 冒泡排序的时间复杂度是：O(n²)
 * 但最好时间复杂度是：O(n)
 * 下面的程序最好时间复杂度仍是 O(n²),所以要优化
 */
public class BubbleSort implements Sort {
    public static void main(String[] args) {
        int[] nums = {111, 3, 5, -9, 0, 4, 2};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(nums);

        SortUtils.check(bubbleSort);
    }

    @Override
    public void sort(int[] nums) {
        //执行 n 次
        for (int i = nums.length - 1; i > 0; i--) {
            //(n-1) + (n-2) + ... + 1 = (n²-n)/2
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    SortUtils.swap(nums, j, j + 1);
                }
            }
//            System.out.println(i + " - " + JSON.toJSONString(nums));
        }
    }
}
