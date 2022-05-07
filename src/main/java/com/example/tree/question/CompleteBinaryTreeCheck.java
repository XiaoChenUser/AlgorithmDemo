package com.example.tree.question;

import com.example.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树（Complete Binary Tree）：
 * 定义：一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，
 * 如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树。
 *
 * 判定方法：
 * 宽度遍历二叉树，满足以下两个条件的为完全二叉树：
 * ①如果某个节点有右节点，则必有左节点；
 * ②满足①的条件下，如果遇到某个节点只有左节点，则该节点之后的所有节点都是叶子节点。
 */
public class CompleteBinaryTreeCheck {

    public static boolean check(Node head){
        if(head==null)
            return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        //是否出现过只有左孩子的节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if(head.right!=null && head.left==null)
                return false;
            if (leaf && head.left != null) {
                return false;
            }
            if (head.left != null) {
                queue.add(head.left);
            }
            if(head.right!=null){
                queue.add(head.right);
            }
            if(head.left!=null && head.right==null){
                leaf = true;
            }
        }
        return true;
    }

    //未检查
    public static boolean check2(Node head){
        if(head==null)
            return true;
        if(head.right!=null && head.left==null)
            return false;
        else if(head.right==null && head.left!=null){
            if(head.left.left!=null || head.left.right!=null)
                return false;
        }
        boolean check = check2(head.left);
        if(!check)
            return false;
        return check2(head.right);
    }
}
