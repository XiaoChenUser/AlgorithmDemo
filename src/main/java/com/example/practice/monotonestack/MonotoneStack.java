package com.example.practice.monotonestack;

import java.util.*;

/**
 * 单调栈：一个数组，找出某个位置左右两边离它最近的比它大的元素。
 */
public class MonotoneStack {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 7, 2, 3, 0, 1};
        Integer[][] closestLargerValue = getClosestLargerValue(arr);
        for (int i = 0; i < closestLargerValue.length; i++) {
            System.out.println(closestLargerValue[i][0] + ":" + closestLargerValue[i][1]);
        }
    }

    public static Integer[][] getClosestLargerValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Integer[][] result = new Integer[arr.length][2];
        Stack<LinkedList<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek().peekLast()]<arr[i]) {
                LinkedList<Integer> idx = stack.pop();
                while (!idx.isEmpty()){
                    int index = idx.pollLast();
                    result[index][1] = i;
                }
            }
            if(!stack.empty() && arr[stack.peek().peekLast()]==arr[i]){
                LinkedList<Integer> idx = stack.pop();
                result[i][0] = result[idx.peekLast()][0];
                idx.addLast(i);
                stack.add(idx);
            }
            if (stack.empty() || (!stack.empty() && arr[stack.peek().peekLast()] > arr[i])) {
                LinkedList<Integer> idx = new LinkedList<>(Arrays.asList(i));
                result[i][0] = stack.empty()?-1:stack.peek().peekLast();
                stack.add(idx);
            }
        }

        while (!stack.empty()) {
            LinkedList<Integer> idx = stack.pop();
            for (Integer index :
                    idx) {
                result[index][1] = -1;
                result[index][0] = stack.empty()?-1:stack.peek().peekLast();
            }
        }
        return result;
    }

    public int[][] getNearLess(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        // 遍历数组，入栈
        for (int i = 0; i < arr.length; ++i) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int popi : popIs) {
                    ans[popi][0] = leftLessIndex;
                    ans[popi][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int popi : popIs) {
                ans[popi][0] = leftLessIndex;
                ans[popi][1] = -1;
            }
        }
        return ans;
    }
}
