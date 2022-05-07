package com.example.tree.iterator;

import com.example.tree.Node;

import java.util.Stack;

/**
 * 二叉树非递归方式遍历。
 */
public class NoRecursionIterator {

    /**
     * 先序遍历： head->left->right
     * 步骤：
     * 弹出头结点，压入右节点，再压入左节点
     * @param head
     */
    public static void preOrder(Node head) {
        System.out.print("pre-order:");
        if(head==null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.val + " ");
            if(head.right!=null)
                stack.push(head.right);
            if(head.left!=null)
                stack.push(head.left);
        }
        System.out.println();
    }

    /**
     * ①左边界节点依次入栈；
     * ②弹出一个节点，打印，同时其右节点重复左边界节点入栈；
     * @param head
     */
    public static void inOrder(Node head) {
        System.out.print("in-order:");
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if(head!=null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    /**
     * ①准备两个栈：一个中间栈，一个结果栈；
     * ②中间栈弹出一个节点，放入结果栈；
     * ③中间栈压入左节点，压入右节点；
     * @param head
     */
    public static void postOrder(Node head) {
        System.out.print("post-order:");
        if(head==null)
            return;
        Stack<Node> temp = new Stack<>();
        Stack<Node> result = new Stack<>();
        temp.push(head);
        while (!temp.isEmpty()){
            head = temp.pop();
            result.push(head);
            if(head.left!=null)
                temp.push(head.left);
            if(head.right!=null)
                temp.push(head.right);
        }
        while (!result.isEmpty()) {
            System.out.print(result.pop().val + " ");
        }
        System.out.println();
    }
}
