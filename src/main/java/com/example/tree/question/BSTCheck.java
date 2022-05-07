package com.example.tree.question;

import com.example.tree.Node;

/**
 * 判断当前树是否是搜索二叉树。
 * 搜索二叉树：一棵树要么为空树，要么任意一个子树，其左子树任意一个节点的值都小于根节点的值，右子树任意一个节点的值都大于根节点的值。
 * 默认 Binary Search Tree 中不存在重复的元素。
 *
 * 题解：BST 的中序遍历满足值升序。
 */
public class BSTCheck {
    public static int preValue = Integer.MIN_VALUE;

    public static boolean check2(Node head) {
        if(head==null)
            return true;
        boolean isBST = check2(head.left);
        if (!isBST) {
            return false;
        }
        if(head.val<=preValue){
            return false;
        }else{
            preValue = head.val;
        }
        return check2(head.right);
    }

    public static boolean check3(Node head) {
        return process(head).isBST;
    }
    public static class Info{
        public Boolean isBST;
        public int minVal;
        public int maxVal;

        public Info(boolean isBST, int minVal, int maxVal) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public static Info process(Node head) {
        if(head==null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.maxVal >= head.val)) {
            isBST = false;
        }
        if (rightInfo != null && (!rightInfo.isBST || rightInfo.minVal <= head.val)) {
            isBST = false;
        }
        int minVal = head.val;
        int maxVal = head.val;
        if(leftInfo!=null) {
            minVal = Math.min(leftInfo.minVal, minVal);
            maxVal = Math.max(leftInfo.maxVal, maxVal);
        }
        if(rightInfo!=null) {
            minVal = Math.min(rightInfo.minVal, minVal);
            maxVal = Math.max(rightInfo.maxVal, maxVal);
        }

        return new Info(isBST, minVal, maxVal);
    }

    /**
     *
     * 错误！！！
     *
     * e.g: pre-order: 3,5,2,6; in-order:3,2,5,6
     * 不能保证左子树的右节点小于根节点，也不能保证右子树的左节点大于根节点。
     * @param head
     * @return
     */
    public static boolean check(Node head) {
        if(head==null)
            return true;
        preValue = head.val;
        if (head.left != null && head.left.val > preValue) {
            return false;
        }else{
            check(head.left);
        }
        if (head.right != null && head.right.val < preValue) {
            return false;
        }else{
            check(head.right);
        }
        return true;
    }
}
