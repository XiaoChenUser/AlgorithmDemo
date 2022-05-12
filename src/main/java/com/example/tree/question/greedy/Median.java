package com.example.tree.question.greedy;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * 中位数（Median）：
 * 有一个整数流，求取任意时刻已经消费数据的中位数。
 */
public class Median {
    /**
     * ①来一个数 cur，如果 cur <= 大根堆的堆顶元素，压入大根堆，否则入小根堆；
     * ②如果两个堆的元素数量之差大于等于 2，将多的（堆顶）元素放入数量少的堆；
     * ③根据堆顶元素及元素总量返回中位数。
     * 中位数：总数是奇数，返回中间的那个数；总数是偶数，返回中间两个数的平均值。
     * @param intStream
     * @return
     */
    public static Integer median(IntStream intStream){
        PriorityQueue<Integer> aesQueue = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        PriorityQueue<Integer> descQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        intStream.forEach(cur -> {
            if (descQueue.isEmpty() || cur <= descQueue.peek()) {
                descQueue.add(cur);
            }else{
                aesQueue.add(cur);
            }
            if (Math.abs(descQueue.size() - aesQueue.size()) >= 2) {
                if (descQueue.size() > aesQueue.size()) {
                    aesQueue.add(descQueue.poll());
                }else{
                    descQueue.add(aesQueue.poll());
                }
            }
        });
        if(aesQueue.isEmpty() && descQueue.isEmpty()){
            return null;
        }else if(aesQueue.isEmpty()){
            return descQueue.peek();
        }else if(descQueue.isEmpty()){
            return aesQueue.peek();
        }else {
            if ((aesQueue.size() + descQueue.size()) % 2 == 0) {
                return (aesQueue.peek() + descQueue.peek()) / 2;
            } else {
                if (aesQueue.size() > descQueue.size()) {
                    return aesQueue.peek();
                } else {
                    return descQueue.peek();
                }
            }
        }
    }
}
