package com.example.list.intersect;

import com.example.list.bean.SinglyListNode;

/**
 * 无环链表相交问题
 * 若相交，返回第 1 个相交的节点；否则，返回 null.
 */
public class NoLoopIntersectionList {
    /**
     * 方法一：
     * ①先遍历至两个链表的尾结点，同时记录两条链表的长度 size1 和 size2，若尾结点内存不相同，则必不相交；
     * ②若两个链表相交，则从第 1 个相交点到尾结点的所有节点，都是公共节点；
     * ③让较长的链表先遍历 |size1-size2| 个节点，再让两个链表同时开始遍历，则第 1 个相同的节点即为两个链表的初次相遇节点。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗：44.7 MB, 在所有 Java 提交中击败了5.12%的用户
     * @param head1
     * @param head2
     * @return
     */
    public static SinglyListNode getIntersectionNode(SinglyListNode head1, SinglyListNode head2) {
        if(head1==null || head2==null)
            return null;
        SinglyListNode temp1 = head1, temp2 = head2;
        int size1 = 0, size2 = 0;
        //temp1 -> list1.tail
        while (temp1.next != null) {
            temp1 = temp1.next;
            size1++;
        }
        //temp2 -> list2.tail
        while (temp2.next!=null){
            temp2 = temp2.next;
            size2++;
        }
        //尾结点不相同，一定不相交
        if (temp1 != temp2) {
            return null;
        }
        //将长链表赋给 temp1，size1 为两个链表长度的差值
        if (size1 > size2) {
            temp1 = head1;
            temp2 = head2;
            size1 -= size2;
        }else{
            temp1 = head2;
            temp2 = head1;
            size1 = size2 - size1;
        }
        //让长的链表先走
        while (size1 > 0) {
            temp1 = temp1.next;
            size1--;
        }
        //相遇点即为两个无环链表第 1 次相交点
        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.40%的用户
     * 内存消耗：44.4 MB, 在所有 Java 提交中击败了23.82%的用户
     * @param headA
     * @param headB
     * @return
     */
    public static SinglyListNode getIntersectionNode2(SinglyListNode headA, SinglyListNode headB) {
        if(headA==null || headB==null)
            return null;
        SinglyListNode tempA = headA, tempB = headB;
        //第 1 轮遍历：A 从 headA 开始遍历,B 从 headB 开始遍历；
        //第 2 轮遍历：A 遍历完，则 tempA = headB 再开始遍历；B 遍历完，则 tempB = headA 再开始遍历；
        //循环条件为 A 和 B 不相等，若两个链表不相交，则第 2 轮遍历时，A,B 将同时到达链表尾，null==null,return null;
        //若相交，则返回第 1 个交点。
        while (tempA != tempB) {
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }
}
