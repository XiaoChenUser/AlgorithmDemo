package com.example.sort.merge;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

//归并排序：使用递归，递归终止条件是只剩一个元素（left==right)，
// 触发 sort() 方法 left==right，从而 return
public class MergeSort implements Sort {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
//        int[] nums = {1, 3, 5, 6, 0, 2, 4, 7};
//        mergeSort.sort(nums, 0, nums.length - 1);
//        System.out.println(Arrays.toString(nums));
        SortUtils.check(mergeSort);
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int left, int right) {
        if(left==right)
            return;
        //分成两半
        int mid = left + (right - left) / 2;
        //左边排序
        sort(nums, left, mid);
        //右边排序
        sort(nums, mid + 1, right);
        //左右都排好序后，merge()
        merge(nums, left, mid + 1, right);
    }

    public void merge(int[] nums, int leftPointer, int rightPointer, int rightBound) {
        int[] temp = new int[rightBound - leftPointer + 1];
        int i = leftPointer;
        int j = rightPointer;
        int k = 0;

        while (i<rightPointer && j<= rightBound){
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i<rightPointer){
            temp[k++] = nums[i++];
        }
        while (j <= rightBound) {
            temp[k++] = nums[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            nums[leftPointer + m] = temp[m];
        }
    }
}
