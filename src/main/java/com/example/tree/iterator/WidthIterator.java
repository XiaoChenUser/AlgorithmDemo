package com.example.tree.iterator;

import com.alibaba.fastjson.JSON;
import com.example.tree.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 按宽度分层遍历栈，返回栈的最大宽度。
 */
public class WidthIterator {

    /**
     * 使用 HashMap 统计各个节点的分层信息 level。
     * 每次 poll 出节点，入队 leftNode 和 rightNode，
     * 同时比较当前节点的 level 和 curLevel 是否相同，不同则结算当前 level 的 maxWidth.
     * @param head
     * @return
     */
    public static int maxWidth(Node head) {
        if(head==null)
            return 0;
        int curLever = 1;
        int curLeverNodes = 0;
        int maxNodes = Integer.MIN_VALUE;
        HashMap<Node,Integer> nodeLevelMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        nodeLevelMap.put(head, curLever);
        while (!queue.isEmpty()){
            head = queue.poll();
            if(head.left!=null) {
                queue.add(head.left);
                nodeLevelMap.put(head.left, curLever + 1);
//                System.out.println(head.left.val + " -> " + nodeLevelMap.get(head.left));
            }
            if(head.right!=null) {
                queue.add(head.right);
                nodeLevelMap.put(head.right, curLever + 1);
//                System.out.println(head.right.val + " -> " + nodeLevelMap.get(head.right));
            }

            int level = nodeLevelMap.get(head);
            if(level==curLever){
                curLeverNodes++;
            }else{
                maxNodes = Math.max(maxNodes, curLeverNodes);
                curLever = level;
                curLeverNodes = 1;
            }
        }
        return maxNodes;
    }

    /**
     * 不使用 HashMap，每次 poll 出节点都把其左右子节点放入队列，
     * 同时更新 nextLevelEndNode，若当前节点和 curLevelEndNode 相同时，
     * 则说明到了当前层的最后节点，结算 maxWidth，重置 curLevelNodes，
     * 同时将 nextLevelEndNode 赋值给 curLevelEndNode，以此来进行栈的分层统计。
     * @param head
     * @return
     */
    public static int maxWidth2(Node head) {
        if (head == null)
            return 0;
        Node curLevelEndNode = head;
        Node nextLevelEndNode = null;
        int curLevelNodes = 0;
        int maxWidth = Integer.MIN_VALUE;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            //这里直接 ++ 是因为 curLevelNodes 从 0 开始，即使 head 是当前层最后一个节点也要 ++
            curLevelNodes++;
            if(head.left!=null){
                nextLevelEndNode = head.left;
                queue.add(head.left);
            }
            if(head.right!=null){
                nextLevelEndNode = head.right;
                queue.add(head.right);
            }
            //queue.poll() 必定会返回当前层的最后一个节点，导致 curLevelNodes 重置
            if (head == curLevelEndNode) {
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevelNodes = 0;
                curLevelEndNode = nextLevelEndNode;
                nextLevelEndNode = null;
            }
        }
        return maxWidth;
    }
}
