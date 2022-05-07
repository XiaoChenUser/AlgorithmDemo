package com.example.sort.count;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(N+K)
 * 稳定性：不稳定
 */
public class CountSort implements Sort {
    public static void main(String[] args) {
        CountSort countSort = new CountSort();
//        int[] nums = {3, 8, 0, 9, 2, 1, 4, 1, 0, 6, 8, 3, 7};
//        countSort.sort(nums);
//        System.out.println(Arrays.toString(nums));
        SortUtils.check(countSort,10);
    }

    @Override
    public void sort(int[] nums) {
        //假设数组取值范围 0-9
        int[] count = new int[10];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        for (int i = 0,j=0; i < count.length; i++) {
            while (count[i]-- > 0) {
                nums[j++] = i;
            }
        }
    }
}
