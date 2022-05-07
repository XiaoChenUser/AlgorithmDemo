package com.example.tree.iterator;

import com.example.tree.Node;

public class RecursionIterator {

    public static void preOrderIterator(Node head) {
        if(head==null)
            return;
        System.out.print(head.val + " ");
        preOrderIterator(head.left);
        preOrderIterator(head.right);
        System.out.println();
    }

    public static void inOrderIterator(Node head) {
        if(head==null)
            return;
        inOrderIterator(head.left);
        System.out.print(head.val + " ");
        inOrderIterator(head.right);
        System.out.println();
    }

    public static void postOrderIterator(Node head) {
        if(head==null)
            return;
        postOrderIterator(head.left);
        postOrderIterator(head.right);
        System.out.print(head.val + " ");
        System.out.println();
    }
}
