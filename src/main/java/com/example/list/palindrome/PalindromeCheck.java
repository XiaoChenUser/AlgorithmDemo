package com.example.list.palindrome;

import com.example.list.bean.SinglyListNode;

import java.util.Stack;

/**
 * 判断单链表是否是回文单链表。
 * 要求：
 * 时间复杂度：O(N),
 * 空间复杂度：O(1)
 */
public class PalindromeCheck {
    /**
     * 原理：
     * 快慢指针 + 栈
     * 快指针走完，慢指针走到中间，通过慢指针将后半部分元素入栈，再从链表头遍历到中间（慢指针）位置，配合出栈，依次比较元素是否相等。
     *
     * 不推荐！！！
     * 性能：
     * 执行用时：22 ms, 在所有 Java 提交中击败了10.16%的用户
     * 内存消耗：56.3 MB, 在所有 Java 提交中击败了59.84%的用户
     * @param head
     * @return
     */
    public boolean isPalindrome(SinglyListNode head) {
        if(head==null || head.next==null)
            return true;
        SinglyListNode slow = head,fast=head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //入栈
        Stack<SinglyListNode> stack = new Stack<>();
        fast = slow;
        while (fast != null) {
            stack.push(fast);
            fast = fast.next;
        }

        //出栈
        fast = head;
        while (fast != slow.next && !stack.isEmpty()) {
            if (fast.val == stack.pop().val) {
                fast = fast.next;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针 + 后半部分链表逆序
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.10%的用户
     * 内存消耗：57.5 MB, 在所有 Java 提交中击败了44.08%的用户
     * @param head
     * @return
     */
    public boolean isPalindrome2(SinglyListNode head) {
        if(head==null || head.next==null)
            return true;
        SinglyListNode slow = head,fast=head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //后半部分逆序
        //                                           null
        //                                            ^
        //                                            |
        // 1 -> 2 -> 3 -> 2 -> 1            1 -> 2 -> 3 <- 2 <- 1
        //           ^         ^                      ^         ^
        //           |         |       =>             |         |
        //         slow       fast                   slow      fast,temp
        fast = slow;
        SinglyListNode temp = slow;
        SinglyListNode temp2 = temp.next;
        slow.next = null;
        while (temp2 != null) {
            temp = temp2;
            temp2 = temp2.next;
            temp.next = fast;
            fast = temp;
        }

        //从两端遍历比较值是否相等
        temp2 = head;
        while (temp != null && temp2 != null) {
            if (temp.val != temp2.val) {
                return false;
            }else{
                temp = temp.next;
                temp2 = temp2.next;
            }
        }
        //到这里说明原链表是回文，但要将其恢复原状
        temp = fast;
        temp2 = temp.next;
        fast.next = null;
        while (temp2 != null) {
            temp = temp2;
            temp2 = temp2.next;
            temp.next = fast;
            fast = temp;
        }

        //输出恢复后的链表
        fast = head;
        while (fast != null) {
            System.out.print(fast.val + " ");
            fast = fast.next;
        }
        System.out.println();

        return true;
    }

    //3.递归
    //满足“时间复杂度 O(N) 和 空间复杂度 O(1)”
    //执行用时：16 ms, 在所有 Java 提交中击败了16.18%的用户
    //内存消耗：58.3 MB, 在所有 Java 提交中击败了15.14%的用户
    SinglyListNode temp;
    public boolean isPalindrome3(SinglyListNode head) {
        temp = head;
        return check(head);
    }

    private boolean check(SinglyListNode head) {
        if (head == null)
            return true;
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }
}
