package com.example.sort.quick;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

/**
 * 三向切分的快速排序
 * 算法原理：
 * 初始状态：i=left,k=i+1,j=right,pivot=nums[left]
 * 移动 k 和 j 遍历数组，使 [left,i) 的元素 < pivot; [i,k) 的元素 == pivot; [k,j] 的元素是还未遍历的元素; (j,right] 的元素 > pivot.
 * 直到 k>j.
 * 再递归遍历 [left,i-1] 和 [k,right] 的元素。
 *
 */
public class QuickSort3Way implements Sort {
    public static void main(String[] args) {
        QuickSort3Way quickSort = new QuickSort3Way();
//        int[] nums = {1, 3, 5, 6, 0, 2, 4, 7};
//        quickSort.sort(nums);
//        System.out.println(Arrays.toString(nums));
        SortUtils.check(quickSort, 10000);
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int pivot = nums[left];
        int i = left, j = right, k = i + 1;
        while (k <= j) {
            if (nums[k] < pivot) {
                SortUtils.swap(nums, i, k);
                i++;
                k++;
            } else if (nums[k] == pivot) {
                k++;
            } else {
                while(nums[j]>pivot && j>=k){
                    j--;
                }
                if(j>=k) {
                    SortUtils.swap(nums, k, j);
                    j--;
                }
            }
        }
//        System.out.println("middle result:" + Arrays.toString(nums));
//        System.out.println("i-1 -> " + (i-1) + ", k -> " + k);
        sort(nums, left, i - 1);
        sort(nums, k, right);
    }
}
