package com.example.list.copy;

import com.example.list.bean.RandomPointerListNode;

import java.util.HashMap;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝。
 * 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 */
public class CopyRandomPointerList {
    /**
     * 1.HashMap
     * hashMap.put(temp,new Node(temp.val));
     * 时间复杂度: O(N)
     * 空间复杂度: O(1)
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了75.33%的用户
     * @param head
     * @return
     */
    public RandomPointerListNode copyRandomList(RandomPointerListNode head) {
        if(head==null)
            return null;
        RandomPointerListNode temp = head;
        HashMap<RandomPointerListNode, RandomPointerListNode> hashMap = new HashMap<>();
        while (temp != null) {
            hashMap.put(temp, new RandomPointerListNode(temp.val));
            temp = temp.next;
        }

        temp = head;
        RandomPointerListNode copy = hashMap.get(head);
        while (temp != null) {
            copy.next = hashMap.get(temp.next);
            copy.random = hashMap.get(temp.random);
            temp = temp.next;
            copy = copy.next;
        }
        return hashMap.get(head);
    }

    /**
     * 2. 1->2->3->null ==> 1->1'->2->2'->3->3'->null
     * 执行用时：0 ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了67.80%的用户
     * @param head
     * @return
     */
    public RandomPointerListNode copyRandomList2(RandomPointerListNode head) {
        if(head==null){
            return null;
        }
        RandomPointerListNode temp = head;
        RandomPointerListNode copy = null;
        //1->2->3->null ==> 1->1'->2->2'->3->3'->null
        while (temp != null) {
            copy = new RandomPointerListNode(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = temp.next.next;
        }

        //给 copy 链表节点的 random 赋值
        temp = head;
        while (temp != null) {
            if(temp.random!=null)
                temp.next.random = temp.random.next;
            temp = temp.next.next;
        }

        //将复合链表切割为2条
        temp=head;
        RandomPointerListNode dummyHead = new RandomPointerListNode(-1);
        copy = dummyHead;
        while(temp!=null){
            copy.next = temp.next;
            copy = copy.next;
            temp.next = temp.next.next;
            temp = temp.next;
        }

        return dummyHead.next;
    }
}
