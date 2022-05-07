package com.example.sort.quick;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

public class QuickSort implements Sort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
//        int[] nums = {1, 3, 5, 6, 0, 2, 4, 7};
////        int[] nums = {2, 2, 2, 2, 2, 2};
//        quickSort.sort(nums);
//        System.out.println(Arrays.toString(nums));
        SortUtils.check(quickSort,10000);
    }


    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums,int low, int high) {
        if(low>=high)
            return;
        int pivot = partition(nums, low, high);
//        System.out.println("middle result:" + Arrays.toString(nums));
        sort(nums, low, pivot - 1);
        sort(nums, pivot + 1, high);
    }

    //    public int partition(int[] nums, int low, int high) {
//        int pivot = low;
//        low += 1;
//        while (low < high) {
//            while (nums[low] < nums[pivot] && low < high) {
//                low++;
//            }
//            while (nums[high] >= nums[pivot] && high > low) {
//                high--;
//            }
//            if (low < high) {
//                swap(nums, low, high);
//                low++;
//                high--;
//            }
//        }
//        if (nums[low] >= nums[pivot]) {
//            swap(nums, pivot, low - 1);
//            return low - 1;
//        }else {
//            swap(nums, pivot, low);
//            return low;
//        }
//    }

    /**
     * partition 直接 pivot=nums[right],
     * 数据划分成 2 个部分：<= pivot, >pivot
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] nums, int left, int right){
        int pivot = nums[right];
        int i=left,j=right-1;
        while (i <= j) {
            if (nums[i] <= pivot) {
                i++;
            }else {
                SortUtils.swap(nums, i, j);
                j--;
            }
        }
        //循环结束，j 为 <= pivot 的右侧边界
        SortUtils.swap(nums, j+1, right);
        return j+1;
    }

    /**
     * partition2 直接 pivot=nums[right]
     * 数据划分成 3 个部分：< pivot, == pivot, > pivot.
     *
     * 最差时间复杂度：O(N^2)
     * nums = {1,2,3,4,5,6,7}
     * @param nums
     * @param left
     * @param right
     */
    public void partition2(int[] nums, int left, int right) {
        //参考 partition3
    }

    /**
     * partition3 产生一个随机的 index，swap(index,right),pivot=nums[right].
     * 数据划分成 3 个部分：< pivot, == pivot, > pivot.
     *
     * 时间复杂度：O(NlogN)
     * @param nums
     * @param left
     * @param right
     */
    public void partition3(int[] nums, int left, int right) {
        //随机取一个数，和 right 交换，使得 pivot 取到每一个数的概率相等，都是 1/n，则改进的 quick sort 时间复杂度为 O(NlogN)
        int random = (int) (Math.random() * (left + (right - left) / 2));
        SortUtils.swap(nums,random,right);
        int pivot = nums[right];
        //i 为 <pivot 的右边界，j 为 >pivot 的左边界，初始值都不在 [left,right] 的范围内
        int i=left-1,k=left,j=right+1;
        //不能等于
        while (k < j) {
            if (nums[k] < pivot) {
                i++;
                SortUtils.swap(nums, i, k);
                k++;
            } else if (nums[k] == pivot) {
                k++;
            }else{
                j--;
                SortUtils.swap(nums, k, j);
                //原来的 j 位置的值大小未知，所以 k 保持不变
            }
        }
        SortUtils.swap(nums, j, right);
        //[left,i]<pivot, [i+1,j]==pivot, 其中 j 是原来的 pivot, [j+1,right]>pivot
        partition3(nums, left, i);
        partition3(nums, j + 1, right);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
