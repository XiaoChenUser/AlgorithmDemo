package com.example.list.intersect;

import com.example.list.ListUtils;
import com.example.list.bean.SinglyListNode;

import java.util.Random;

/**
 * 两个链表可能有环也可能无环 headA,headB,
 * 若相交,返回第 1 个交点,否则,返回 null.
 */
public class LoopIntersectionList {
    public static void main(String[] args) {
        LoopIntersectionList list = new LoopIntersectionList();
        //test
        SinglyListNode head1 = null;
        SinglyListNode head2 = null;
        SinglyListNode tail2 = null;
        SinglyListNode loopHead1 = null;
        SinglyListNode loopHead2 = null;
        //①无环链表不相交
        head1 = ListUtils.getNoLoopList(15);
        head2 = ListUtils.getNoLoopList(5);

        //②无环链表相交
        SinglyListNode temp = head2;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head1.next.next.next.next;

        //③有环链表不相交
        head1 = ListUtils.getNoLoopList(5);
        ListUtils.sout("origin head1", head1, null);
        loopHead1 = ListUtils.getLoop(6);
        ListUtils.getLoopList(head1, loopHead1);

//        head2 = ListUtils.getNoLoopList();
//        loopHead2 = ListUtils.getLoop(6);
//        ListUtils.getLoopList(head2, loopHead2);
        //有环链表相交
        //④有环链表相交于环外
        head2 = ListUtils.getNoLoopList();
        ListUtils.sout("origin head2", head2, null);
        tail2 = ListUtils.getTail(head2);
        tail2.next = head1.next.next.next;
        //⑤有环链表相交于环上
//        tail2.next = loopHead1.next.next;
        //⑥特殊链表：[],[1],
//        head2 = null;
//        head2 = new SinglyListNode(1);

        ListUtils.sout("head1", head1, loopHead1);
        ListUtils.sout("head2", head2, loopHead1);

        SinglyListNode intersectionNode = list.getIntersectionNode(head1, head2);
        if(intersectionNode==null){
            System.out.println("不相交");
        }else{
            System.out.println("相交于：" + intersectionNode + " : " + intersectionNode.val);
        }
    }


    public SinglyListNode getIntersectionNode(SinglyListNode headA, SinglyListNode headB) {
        SinglyListNode loopNode1 = getLoopNode(headA);
        SinglyListNode loopNode2 = getLoopNode(headB);
        //若相交,loopNode1 & loopNode2 只能同时为 null，或者同时不为 null
        if(loopNode1==null && loopNode2==null){
            //无环链表是否相交问题
            return NoLoopIntersectionList.getIntersectionNode(headA, headB);
        }else if(loopNode1!=null && loopNode2!=null){
            //有环链表的三种情况：不相交；相交在环外；相交在环上
            //①相交在环外,退化成无环链表相交问题,loopNode1为尾结点
            if(loopNode1==loopNode2)
                return getOutsideLoopIntersectionNode(headA, headB, loopNode1);
            else{
                //②从 loopNode1 开始遍历环,若在 1 圈内不能遇到 loopNode2,则不相交
                SinglyListNode tempA = loopNode1;
                while (tempA != loopNode2) {
                    tempA = tempA.next;
                    if(tempA==loopNode1)
                        return null;
                }
                //③相交在环上,loopNode1 和 loopNode2 都是满足条件的节点,返回任意一个
                return loopNode1;
            }
        }
        return null;
    }

    public SinglyListNode getOutsideLoopIntersectionNode(SinglyListNode headA, SinglyListNode headB, SinglyListNode tail){
        SinglyListNode tempA = headA, tempB = headB;
        while (tempA != tempB) {
            tempA = tempA == tail ? headB : tempA.next;
            tempB = tempB == tail ? headA : tempB.next;
        }
        return tempA;
    }


    /**
     * 判断给定链表中是否有环,若有,返回入环节点,反之,返回 null.
     * 方法：
     * 快慢指针 + 有环链表查找入环节点特殊方法
     * @param head
     * @return
     */
    public SinglyListNode getLoopNode(SinglyListNode head) {
        SinglyListNode fast = head,slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow)
                break;
        }
        //无环
        if(fast==null || fast.next==null)
            return null;
        //slow 位置不变，查看"入环节点.jpg"
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
