package com.example.sort.bubble;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

/**
 * 冒泡排序最好时间复杂度：O(n)
 * 当原始数据有序，在外层第一次遍历（内层比较 n-1 次比较）后，
 * 通过标志位发现没有发生过数据交换，直接返回，此时时间复杂度为 O(n)
 */
public class BubbleSort2 implements Sort {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        BubbleSort2 bubbleSort2 = new BubbleSort2();
        bubbleSort2.sort(nums);
        System.out.println(JSON.toJSONString(nums));
    }

    @Override
    public void sort(int[] nums) {
        for (int i = nums.length-1; i > 0 ; i--) {
            boolean swaped = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swaped = true;
                    SortUtils.swap(nums, j, j + 1);
                }
                System.out.println("j - " + j);
            }
            if(!swaped){
                return;
            }
        }
    }
}
