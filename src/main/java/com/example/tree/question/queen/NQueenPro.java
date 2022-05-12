package com.example.tree.question.queen;

/**
 * 使用位运算来表示对皇后摆放位置的限制。
 * 注意：无法改变时间复杂度，只能改变算法的常数项。
 */
public class NQueenPro {
    //这里限制不要超过 32 位，否则要将 int 换成 long
    public static int solute(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        //limit 构成：n 皇后就让 limit 的右侧有 n 个 1，左侧 [31,n] 都为 0，limit 始终保持不变
        //limit 右侧的 1 表示所有可以放皇后的位置
        int limit = n == 32 ? -1 : (1 << n) - 1;
        //处理第 1 行时，无任何限制
        return process(limit, 0, 0, 0);
    }

    /**
     * 假设已经摆过前 i 行的皇后，构造 [0,i-1] 行皇后对第 i 行皇后位置的影响。
     * colLimit,leftLimit,rightLimit 中为 1 的位表示不能放置皇后.
     * 例如：8皇后问题，假设第 1 行摆在 4，
     * 即：00010000
     * 则：
     * colLimit: 00100000
     * leftLimit:00100000
     * rightLimit:00001000
     * @param limit 保持不变
     * @param colLimit 前 i 行对下一行列的限制
     * @param leftLimit 前 i 行对下一行左斜线的限制
     * @param rightLimit 前 i 行对下一行右斜线的限制
     * @return
     */
    public static int process(int limit, int colLimit, int leftLimit, int rightLimit) {
        if (colLimit == limit) {
            return 1;
        }
        int res = 0;
        int pos = limit & ~(colLimit | leftLimit | rightLimit);
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += process(limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >>> 1);
        }
        return res;
    }
}
