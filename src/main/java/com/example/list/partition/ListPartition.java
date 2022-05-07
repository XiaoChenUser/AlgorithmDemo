package com.example.list.partition;

import com.example.list.bean.MySinglyLinkedList;
import com.example.list.bean.SinglyListNode;
import com.example.sort.quick.QuickSort;
import com.example.sort.utils.SortUtils;

import java.util.Arrays;

/**
 * 给定一个链表和一个指定的值，将 <val 的放在左边，==val 的放在中间，>val 的放在右边。
 * 要求：
 * 稳定性：稳定
 */
public class ListPartition {
    public SinglyListNode partition(SinglyListNode head, int val) {
        if(head==null)
            return null;
        SinglyListNode temp = head;
        SinglyListNode smallHead = null;
        SinglyListNode smallTail = null;
        SinglyListNode equalHead = null;
        SinglyListNode equalTail = null;
        SinglyListNode bigHead = null;
        SinglyListNode bigTail = null;
        while (temp != null) {
            if (temp.val < val) {
                if (smallHead == null) {
                    smallHead = temp;
                }else{
                    smallTail.next = temp;
                }
                smallTail = temp;
            } else if (temp.val == val) {
                if (equalHead == null) {
                    equalHead = temp;
                }else {
                    equalTail.next = temp;
                }
                equalTail = temp;
            }else {
                if (bigHead == null) {
                    bigHead = temp;
                }else{
                    bigTail.next = temp;
                }
                bigTail = temp;
            }

            temp = temp.next;
        }

        if(smallHead==null){
            if (equalHead == null) {
                head = bigHead;
            }else {
                equalTail.next = bigHead;
                head = equalHead;
            }
        }else {
            if (equalHead == null) {
                smallTail.next = bigHead;
            }else{
                smallTail.next = equalHead;
                equalTail.next = bigHead;
            }
            head = smallHead;
        }
        return head;
    }

}
