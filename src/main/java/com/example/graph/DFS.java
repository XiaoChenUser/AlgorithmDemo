package com.example.graph;

import com.example.graph.bean.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历（Depth First Search）：
 * 1.利用栈实现；
 * 2.从源节点开始把节点按照深度放入栈，然后弹出；
 * 3.每弹出一个点，若其存在一个未进过栈的 next 节点，则将该节点和 next 节点重新入栈，且不再遍历后续 next 节点；
 * 4.直到栈为空。
 */
public class DFS {
    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next :
                    cur.nexts) {
                if (!set.contains(next)) {
                    System.out.println(next);
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    break;
                }
            }
        }
    }
}
