package com.example.practice.manacher;

public class Manacher {
    public static void main(String[] args) {
        String str = "1221";
        System.out.println(getMaxPalindromeLength(str));
    }

    public static int getMaxPalindromeLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        //1.add virtual character '#'
        char[] arr = new char[2 * str.length() + 1];
        arr[0] = '#';
        for (int i = 0; i < str.length(); i++) {
            arr[2*i+1] = str.charAt(i);
            arr[2 * i + 2] = '#';
        }
        System.out.println(new String(arr));

        //2.palindrome radius array
        int[] radius = new int[arr.length];
        //回文中心
        int C = -1;
        //这里有些不一样，R 是回文边界的下一个位置，即回文区域只到 R-1
        int R = -1;
        //最大回文半径
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != arr.length; i++) {
            //每个位置最小的回文长度：radius[i'],R-i,1
            radius[i] = i < R ? Math.min(radius[2 * C - i], R - i) : 1;
            //向外扩展
            //当以 i 为中心的左右边界还在 arr 的范围内
            while (i + radius[i] < arr.length && i - radius[i] > -1) {
                if (arr[i + radius[i]] == arr[i - radius[i]]) {
                    radius[i]++;
                }else{
                    break;
                }
            }
            if (i + radius[i] > R) {
                R = i+radius[i];
                C = i;
            }
            max = Math.max(max, radius[i]);
        }
        //带虚拟字符的最大回文半径减1就是原字符串的最大回文长度
        //
        return max-1;
    }

}
