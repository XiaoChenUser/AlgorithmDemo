package com.example.tree.question.queen;

/**
 * N 皇后问题：
 * 在一个 N*N 的棋盘上摆 N 个皇后，要求任意两个皇后不同行，不同列，不同斜线，有多少种摆法？
 * 已知：
 * n=1,1;
 * n=2,0;
 * n=3,0;
 * n=8,92;
 * 时间复杂度：O(N^N)
 */
public class NQueen {
    //按照行来排列皇后
    public static int solute(int n) {
        if (n < 1) {
            return 0;
        }
        //使用 record 记录每一行的皇后摆在了哪一列，第 1 行摆在 record[0] 列,...
        int[] record = new int[n];
        return process(record, 0, n);
    }

    /**
     *
     * @param record 记录了 [0,i-1] 行的列
     * @param i 目前来到了第 i 行，前面 [0,i-1] 行已经摆完了
     * @param n 表示一共有多少行
     * @return 在 [0,i-1] 已经摆好的情况下，[i,n-1] 的这些区域一共有多少种合理的摆法
     */
    public static int process(int[] record, int i, int n) {
        if(i==n){
            return 1;
        }
        int result = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                result += process(record, i + 1, n);
            }
        }
        return result;
    }

    /**
     * 在 [0,i-1] 已经摆好的情况下，将第 i 行的皇后摆在第 j 列是否合理.
     * @param record
     * @param i
     * @param j
     * @return
     */
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            //分别表示：是否同列，是否同斜线
            if (record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 8};
        for (int i = 0; i < nums.length; i++) {
            int solute = solute(nums[i]);
            System.out.println(nums[i] + ":" + solute);
        }
    }
}
