package com.example.tree.question;

import com.example.tree.Node;

/**
 * 平衡二叉树（英语：Balanced Binary Tree）：
 * 又被称为 AVL 树。
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 */
public class BalancedBinaryTreeCheck {
    public static boolean check(Node head) {
        return process(head).isBalanced;
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(Node head) {
        if(head==null){
            return new Info(true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isBalanced = (leftInfo.isBalanced && rightInfo.isBalanced)
                && (Math.abs(leftInfo.height - rightInfo.height) < 2);
        int height = Math.max(leftInfo.height + 1, rightInfo.height + 1);
        return new Info(isBalanced, height);
    }
}
