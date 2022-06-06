package com.example.practice.deque;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class WindowValue {
    public static void main(String[] args) {
        int[] arr = {1, 3, 9, 7, 6, 4, 0, 5};
        int width = 4;
        int[] maxValueWindow = getMaxValueWindow(arr, width);
        System.out.println(Arrays.toString(maxValueWindow));

        int[] minValueWindow = getMinValueWindow(arr, width);
        System.out.println(Arrays.toString(minValueWindow));
    }

    //获取窗口最大值，deque 从头到尾，元素严格从大到小，有相同的元素，先 pollLast，再 addLast(arr[i]).
    public static int[] getMaxValueWindow(int[] arr, int width) {
        if (arr == null || arr.length == 0 || arr.length<width) {
            return null;
        }
        int len = arr.length - width + 1;
        int[] result = new int[len];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            //1.窗口右端点向右移动，保持 deque 中元素严格从大到小，若 arr[i] < deque.peekLast()，直接从队尾入队；
            //否则 deque.pollLast()，直到队尾元素大于 arr[i]，或者 deque 为空。
            while (!deque.isEmpty() && deque.peekLast() <= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(arr[i]);

            //2.窗口左端点向右移动，poll from first,left 是窗口左端点，left-1 是本次过期的下标
            int left = i - width + 1;
            if (left>=0) {
                if(left>0 && deque.peekFirst()==arr[left-1]) {
                    deque.pollFirst();
                }
                result[left] = deque.peekFirst();
            }
        }
        return result;
    }

    public static int[] getMinValueWindow(int[] arr, int width) {
        if (arr == null || arr.length == 0 || arr.length<width) {
            return null;
        }
        int len = arr.length - width + 1;
        int[] result = new int[len];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() > arr[i]) {
                deque.pollLast();
            }
            deque.addLast(arr[i]);

            int left = i - width + 1;
            if (left>=0) {
                if(left>0 && deque.peekFirst()==arr[left-1]) {
                    deque.pollFirst();
                }
                result[left] = deque.peekFirst();
            }
        }
        return result;
    }
}

























