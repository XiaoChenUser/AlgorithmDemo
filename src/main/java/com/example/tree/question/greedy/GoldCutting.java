package com.example.tree.question.greedy;

import java.util.PriorityQueue;

/**
 * 切金条，每次切割所花费的铜板数等于切割前金条的长度，将金条切割成指定的长度，求花费的最少铜板数。
 * 例如：60 的金条切割成 [10,20,30],若第 1 刀切 10，第 2 刀切 20，花费 60 + 50 = 110 个铜板；
 * 若第 1 刀切 30，第 2 刀切 20，花费 60 + 30 = 90 个铜板。
 * 解题思路：
 * 将最终长度从小到大排序，然后从大到小切割，所花费的铜板最少。
 */
public class GoldCutting {
    public static int minCost(int[] pieces) {
        if (pieces == null || pieces.length <= 1) {
            return 0;
        }
        //小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (Integer piece :
                pieces) {
            priorityQueue.add(piece);
        }
        int cost = 0;
        int cur = 0;
        //每次取出 2 个求和，再压入堆
        while (priorityQueue.size() > 1) {
            cur = priorityQueue.poll() + priorityQueue.poll();
            cost += cur;
            priorityQueue.add(cur);
        }
        return cost;
    }
}
