package com.example.list;

import com.example.list.bean.SinglyListNode;

import java.util.Random;
import java.util.StringJoiner;

public class ListUtils {
    public static SinglyListNode getNoLoopList() {
        Random random = new Random();
        return getNoLoopList(random.nextInt(10)+1);
    }

    public static SinglyListNode getNoLoopList(int size) {
        SinglyListNode dummyHead = new SinglyListNode(-1);
        SinglyListNode temp = dummyHead;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            temp.next = new SinglyListNode(random.nextInt(100));
            temp = temp.next;
        }
        return dummyHead.next;
    }

    public static void getLoopList(SinglyListNode head, SinglyListNode loopHead){
        SinglyListNode tail = getTail(head);
        tail.next = loopHead;
    }

    public static SinglyListNode getLoopList(int size, int loopSize) {
        SinglyListNode head = getNoLoopList(size);
        SinglyListNode loopHead = getLoop(loopSize);
        SinglyListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = loopHead;
        return head;
    }

    public static SinglyListNode getLoop(int size) {
        SinglyListNode head = getNoLoopList(size);
        SinglyListNode tail = getTail(head);
        tail.next = head;
        return head;
    }

    public static void sout(String name, SinglyListNode head, SinglyListNode loopHead) {
        SinglyListNode temp = head;
        int n = 0;
        System.out.print(name + ":");
        StringJoiner joiner = new StringJoiner("->");
        while (temp != null && n!=2) {
            if (loopHead != null && temp == loopHead) {
                n++;
            }
            joiner.add(String.valueOf(temp.val));
            temp = temp.next;
        }
        System.out.print(joiner.toString());
        System.out.println();
    }

    public static SinglyListNode getTail(SinglyListNode head) {
        if(head==null)
            return null;
        SinglyListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
//        System.out.println("tail:" + tail.val);
        return tail;
    }

}
