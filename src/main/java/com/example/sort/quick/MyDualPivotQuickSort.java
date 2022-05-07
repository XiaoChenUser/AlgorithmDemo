package com.example.sort.quick;

import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

/**
 * 双轴快速排序
 * 算法原理：
 * 1)取第 1 个和最后一个作为 pivot1 和 pivot2，且 pivot1<=pivot2，pivot1 和 pivot2 指向的 left 和 right 在过程中不参与 swap；
 * 2)取 i=left,k=left+1,j=right-1，这样设置是为了一轮遍历结束，小于 pivot1 的都在最左边，大于等于 pivot1 且小于等于 pivot2 的在中间，
 *  大于 pivot2 的在最右边，而且“ i 指向最后一个小于 pivot1 的元素，j 指向最后一个小于等于 pivot2 的元素 “;
 * 3)将 pivot1, pivot2 放入他们应放的位置：swap(left,i), swap(j+1,right)，则：[left,i)<pivot1, pivot1<=[i,j+1]<=pivot2, (j+1,right]>pivot2;
 * 4)递归 sort(left,i-1), sort(i+1,j), sort(j+2,right).
 * 注意：这里最重要的是，第 4 步中 pivot1, pivot2 是不再参与 sort(...)，否则可能引起循环调用 sort(...)，造成 StackOverflowError!!!
 * e.g: nums = {1,3,5,6,2,4,7}, sort(nums) 会死循环。
 */
public class MyDualPivotQuickSort implements Sort {
    public static void main(String[] args) {
        MyDualPivotQuickSort quickSort = new MyDualPivotQuickSort();
        int[] nums = {1, 3, 5, 6, 0, 2, 4, 7};
        quickSort.sort(nums);
        System.out.println(Arrays.toString(nums));
//        SortUtils.check(quickSort,10000);
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        if (nums[left] > nums[right]) {
            SortUtils.swap(nums, left, right);
        }
        //pivot1 和 pivot2 只在最后才参与 swap
        int pivot1 = nums[left];
        int pivot2 = nums[right];
        int i = left, k = left + 1, j = right-1;
        while (k <= j) {
            if (nums[k] < pivot1) {
                i++;
                SortUtils.swap(nums, k, i);
                k++;
            } else if (nums[k] >= pivot1 && nums[k] <= pivot2) {
                k++;
            } else {
                while (nums[j]>pivot2 && j>=k)
                    j--;
                if (j >= k) {
                    SortUtils.swap(nums, k, j);
                    j--;
                }
            }
        }
        //[left+1,i]<pivot1,pivot1<=[i+1,j]<=pivot2,[j+1,right-1]>pivot2
        //这里不使用 k 是因为当数组元素原本就自增时，当 k>j 时，k 已经超出数组范围.
        SortUtils.swap(nums, left, i);
        SortUtils.swap(nums, right, j + 1);
//        System.out.println("middle result:" + Arrays.toString(nums));
//        System.out.println("i -> " + i + ", j+1 -> " + (j+1));

        //[left,i)<pivot1, pivot1<=[i,j+1]<=pivot2, (j+1,right]>pivot2
        //pivot1(nums[i]) 和 pivot2(nums[j+1]) 不再参与比较，因为 pivot1<=[i+1,j]<=pivot2,
        //而且，如果 pivot1==min(nums),且 pivot2==max(nums),则会使 sort(...) 陷入死循环，造成 StackOverflowError
        sort(nums, left, i - 1);
//        sort(nums, i, j + 1);
        sort(nums, i + 1, j);
        sort(nums, j + 2, right);
    }


}
