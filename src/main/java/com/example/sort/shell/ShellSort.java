package com.example.sort.shell;

import com.alibaba.fastjson.JSON;
import com.example.sort.Sort;
import com.example.sort.utils.SortUtils;

public class ShellSort implements Sort {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] nums = {111, 3, 5, -9, 0, 4, 2};
        shellSort.sort(nums);
        System.out.println(JSON.toJSONString(nums));
//        SortUtils.check(shellSort);
    }

    //Knuth 序列，获取初始的 step 值
    private int knuth(int length){
        int h = 1;
        while (h <= length / 3) {
            h = 3*h +1;
        }
        return h;
    }

    @Override
    public void sort(int[] nums) {
        int step = knuth(nums.length);
        for (; step > 0; step = (step - 1) / 3) {
            for (int i = step; i < nums.length; i++) {
                int temp = nums[i];
                int j;
                for (j = i - step; j >= 0 && nums[j] > temp; j -= step) {
                    nums[j + step] = nums[j];
                }
                nums[j + step] = temp;
            }
        }
    }

//    @Override
//    public void sort(int[] nums) {
//        int step = 4;
//        if (nums.length < step) {
//            stepSort(nums,1);
//        }
//        do {
//            stepSort(nums, step);
//            step=step/2;
//        } while (step>0);
//        stepSort(nums,1);
//    }
//
//    public void stepSort(int[] nums, int step) {
//        for (int i = 0; i < step; i++) {
//            for (int j = i+step; j < nums.length; j+=step) {
//                int temp = nums[j];
//                for (int k = j-step; k >=0; k-=step) {
//                    if (nums[k] > temp) {
//                        nums[k + step] = nums[k];
//                        if (k == i) {
//                            nums[k] = temp;
//                        }
//                    }else {
//                        nums[k+step] = temp;
//                        break;
//                    }
//                }
//            }
//        }
//    }
}
