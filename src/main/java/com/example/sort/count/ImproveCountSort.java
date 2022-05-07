package com.example.sort.count;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

//nums = {0,2,2,1,0,0}
//=>count = {3,1,2}
//=>count' = {3,4,6}
//sorted nums = {0,0,0,1,2,2}
//最后一个 0 的 index = count'[0]-1，最后一个2的下标是 count'[2]-1,...

/**
 * 时间复杂度：O(N+K)
 * 空间复杂度：O(N+K)
 * 稳定性：稳定
 */
public class ImproveCountSort implements Sort {
    public static void main(String[] args) {
        ImproveCountSort countSort = new ImproveCountSort();
        int[] nums = {113, 148, 140, 129, 102, 119, 144, 141, 130, 106, 118, 103, 137};
        countSort.sort(nums);
        System.out.println(Arrays.toString(nums));
//        SortUtils.check(countSort,10);
    }

    @Override
    public void sort(int[] nums) {
        int[] temp = new int[nums.length];
        //计数数组
        //假设取值范围：100-150
        int[] count = new int[51];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]-100]++;
        }
        //计数累加数组
        //计数累加数组 count[i]-1 的值是最后一个 count[i] 的值在排序后的 nums 数组的下标
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        //从后向前遍历 nums 数组，找到其每个元素在排序后 nums 数组中的位置，且是“稳定”的！！！
        for (int i = nums.length-1; i >=0 ; i--) {
            temp[--count[nums[i]-100]] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }


}
