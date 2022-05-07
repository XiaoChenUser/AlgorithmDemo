package com.example.sort.quick;

import com.example.sort.Sort;

import java.util.Arrays;

public class QuickSort2 implements Sort {
    public static void main(String[] args) {
        QuickSort2 quickSort2 = new QuickSort2();
        int[] nums = {7,3,2,10,8,1,9,5,4,6};
        System.out.println("origin:" + Arrays.toString(nums));
//        int[] nums = {2, 2, 2, 2, 2, 2};
        quickSort2.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int left, int right) {
        if(left>=right)
            return;
        int middle = partition(nums, left, right);
        System.out.println("middle result:" + Arrays.toString(nums));
        sort(nums, left, middle - 1);
        sort(nums, middle + 1, right);
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int low = left;
        int high = right - 1;
        while (low <= high) {
            while (low <= high && nums[low] <= pivot) {
                low++;
            }
            while (high>=low && nums[high]>pivot){
                high--;
            }
            if (low < high) {
                swap(nums, low, high);
            }
        }
        swap(nums, low, right);
        return low;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
