package com.example.tree.question.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 使用一笔初始资金承接项目，最多只能承接 K 个，返回最多有多少利润。
 */
public class ProjectProfit {
    public static int profit(Project[] projects, int initMoney, int k) {
        if (projects == null || projects.length == 0 || k <= 0) {
            return 0;
        }
        PriorityQueue<Project> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        minCostQueue.addAll(Arrays.asList(projects));

        int money = initMoney;
        Project project = null;
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= money) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                break;
            }
            project = maxProfitQueue.poll();
            money += project.profit;
        }
        return money - initMoney;
    }

    public static class MinCostComparator implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }

    public static class Project{
        public int cost;
        public int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}
