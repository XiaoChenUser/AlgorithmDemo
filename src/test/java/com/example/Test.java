package com.example;

import com.example.list.bean.RandomPointerListNode;
import com.example.list.partition.ListPartition;
import com.example.list.palindrome.PalindromeCheck;
import com.example.list.bean.MySinglyLinkedList;
import com.example.list.bean.SinglyListNode;
import com.example.tree.Node;
import com.example.tree.iterator.NoRecursionIterator;
import com.example.tree.iterator.RecursionIterator;
import com.example.tree.iterator.WidthIterator;
import com.example.tree.question.BSTCheck;
import com.example.tree.question.ClosestCommonParent;

import java.util.LinkedList;

import static com.example.tree.iterator.RecursionIterator.*;

public class Test {
    @org.junit.Test
    public void linkedListTest(){
        MySinglyLinkedList linkedList = new MySinglyLinkedList();
        linkedList.addAtHead(3);
        linkedList.addAtTail(1);
    }

    @org.junit.Test
    public void isPalindromeTest(){
        MySinglyLinkedList linkedList = new MySinglyLinkedList();
        linkedList.addAtTail(1);
        linkedList.addAtTail(2);
        linkedList.addAtTail(3);
        linkedList.addAtTail(3);
        linkedList.addAtTail(2);
        linkedList.addAtTail(1);
        PalindromeCheck palindromeCheck = new PalindromeCheck();
        boolean isPalindrome = palindromeCheck.isPalindrome2(linkedList.getHead());
        System.out.println(isPalindrome);
    }

    @org.junit.Test
    public void listPartitionTest(){
        MySinglyLinkedList linkedList = new MySinglyLinkedList();
        linkedList.addAtTail(1);
        linkedList.addAtTail(9);
        linkedList.addAtTail(31);
        linkedList.addAtTail(0);
        linkedList.addAtTail(2);
        linkedList.addAtTail(6);
        soutList(linkedList.getHead());

        ListPartition listPartition = new ListPartition();
        linkedList.setHead(listPartition.partition(linkedList.getHead(), 0));
        soutList(linkedList.getHead());
    }

    public static void soutList(SinglyListNode head){
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    @org.junit.Test
    public void treeTest(){
        Node head = getTree();

//        RecursionIterator.preOrderIterator(head);
//        RecursionIterator.inOrderIterator(head);
//        RecursionIterator.postOrderIterator(head);
        NoRecursionIterator.preOrder(head);
        NoRecursionIterator.inOrder(head);
        NoRecursionIterator.postOrder(head);

        int maxWidth = WidthIterator.maxWidth2(head);
        System.out.println("maxWidth:" + maxWidth);
    }

    public Node getTree(){
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);
        head.left = node1;
        head.right = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node5.right = node7;
        return head;
    }

    @org.junit.Test
    public void bstTest(){
        Node head = new Node(3);
        Node node1 = new Node(5);
        Node node2 = new Node(2);
        Node node3 = new Node(6);
        head.right = node1;
        node1.left = node2;
        node1.right = node3;
        boolean isBST = BSTCheck.check2(head);
        System.out.println(isBST);
    }

    @org.junit.Test
    public void ancestorTest(){
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);
        head.left = node1;
        head.right = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node5.right = node7;
        Node ancestor = ClosestCommonParent.getClosestCommonParent(head, node3, node7);
        Node ancestor2 = ClosestCommonParent.getClosestCommonParent2(head, node3, node7);
        System.out.println(ancestor.val);
        System.out.println(ancestor2!=null?ancestor2.val:null);

        new LinkedList<Integer>().add(null);
    }

    @org.junit.Test
    public void bitTest(){
        //获取二进制数右侧第 1 个 1 的位置
        int p = 6;
        int mostRightOne = p & (~p + 1);
        System.out.println(mostRightOne);
    }
}
