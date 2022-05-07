package com.example.list.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用虚拟头结点（dummy head）是为了将对链表头结点 head 的操作变为对普通节点的操作，降低问题的复杂性。
 * 但因为使用 dummy head 完成相应的操作可能影响了原链表的头结点 head，所以最后一定要对 head 重新赋值：
 * head = dummyHead.next;
 * 否则可能会出现 head == null 的情况。
 */
@Data
@NoArgsConstructor
public class MySinglyLinkedList {
    private SinglyListNode head;
    private transient int size;

    public MySinglyLinkedList(SinglyListNode head) {
        this.head = head;
        this.size = 1;
    }

    /**
     * 获取链表中第 index 个节点的值。
     * 如果索引无效，则返回-1.
     * @param index
     * @return
     */
    public int get(int index){
        if(size==0 || index<0 || index>=size)
            return -1;
        SinglyListNode temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点。
     * 插入后，新节点将成为链表的第一个节点。
     * @param val
     * @return new head node
     */
    public SinglyListNode addAtHead(int val){
        return addAtIndex(0, val);
    }

    /**
     * 将值为 val 的节点追加到链表的最后一个元素。
     * @param val
     */
    public void addAtTail(int val){
        addAtIndex(size, val);
    }

    /**
     * 在链表中的第 index 个节点之前添加值为 val 的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。
     * 如果index小于0，则在头部插入节点。
     * @param index
     * @param val
     */
    public SinglyListNode addAtIndex(int index,int val){
        // index -> [0,size]
        if(index<0)
            index = 0;
        else if (index > this.size) {
            return this.head;
        }
        //use dummy head node
        SinglyListNode dummyHead = new SinglyListNode(-1);
        dummyHead.next = head;
        SinglyListNode temp = dummyHead;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        SinglyListNode newNode = new SinglyListNode(val);
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
        head = dummyHead.next;
        return head;
    }

    /**
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     * @param index
     */
    public void deleteAtIndex(int index){
        if(size==0 || index<0 || index>=size)
            return;
        SinglyListNode dummyHead = new SinglyListNode(-1);
        dummyHead.next = head;
        SinglyListNode temp = dummyHead;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        size--;
        temp.next = temp.next.next;
        head = dummyHead.next;
    }
}
