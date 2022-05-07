package com.example.graph;

import com.example.graph.bean.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度（广度）优先遍历（Breadth First Search）：
 * 1.利用队列实现；
 * 2.从源节点开始依次按照宽度进队列，然后弹出；
 * 3.每弹出一个点，把该节点所有没进过队列的邻节点放入队列；
 * 4.直到队列为空.
 */
public class BFS {
    public void bfs(Node node) {
        if(node==null)
            return;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node);
            for (Node next :
                    node.nexts) {
                while (!set.contains(next)) {
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }
}
