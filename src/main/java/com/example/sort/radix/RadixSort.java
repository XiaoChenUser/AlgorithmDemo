package com.example.sort.radix;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

public class RadixSort implements Sort {
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
//        int[] nums = {3, 333, 4999, 0, 89, 32, 44, 10};
//        radixSort.sort(nums);
//        System.out.println(Arrays.toString(nums));
        SortUtils.check(radixSort,10000);
    }

    @Override
    public void sort(int[] nums) {
        int maxDigit = findMaxDigit(nums);
        sort(nums, maxDigit);
    }

    public void sort(int[] nums, int digits) {
        int[] temp = new int[nums.length];
        int[] count = new int[10];
        for (int i = 0; i < digits; i++) {
            int division = (int) Math.pow(10, i);
            for (int k : nums) {
                int num = k / division % 10;
                count[num]++;
            }
//            System.out.println(Arrays.toString(count));
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }
            for (int j = nums.length - 1; j >= 0; j--) {
                int num = nums[j] / division % 10;
                temp[--count[num]] = nums[j];
            }
//            System.out.println(Arrays.toString(temp));
            System.arraycopy(temp, 0, nums, 0, temp.length);
            Arrays.fill(count, 0);
        }
    }

    private int findMaxDigit(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        for (int i = 1; ; i++) {
            if (max / Math.pow(10, i) == 0) {
                return i;
            }
        }
    }
}
