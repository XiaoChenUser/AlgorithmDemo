package com.example.tree.question;

import com.example.tree.Node;

/**
 * 给定树中的两个节点，找出它们的最近公共祖先.
 * 最近公共祖先：两个节点所在的两个子树第 1 次相交的节点。
 * 注：节点 A 是节点 A 自己的祖先。
 *
 * 分为两种情况：
 * ① node1 和 node2 中其中一个是另一个的祖先；
 * ② node1 & node2 祖先和孩子的关系。
 */
public class ClosestCommonParent {

    public static Node getClosestCommonParent(Node head, Node node1, Node node2) {
        if (head == null || head==node1 || head==node2) {
            return head;
        }
        Node left = getClosestCommonParent(head.left, node1, node2);
        Node right = getClosestCommonParent(head.right, node1, node2);
        if(left!=null && right!=null){
            return head;
        }
        return left != null ? left : right;
    }




    public static Node getClosestCommonParent2(Node head, Node node1, Node node2) {
        ReturnType result = process(head, node1, node2);
        if(result!=null && result.isNode1Parent && result.isNode2Parent)
            return result.curNode;
        return null;
    }

    public static class ReturnType{
        public Node curNode;
        public boolean isNode1Parent;
        public boolean isNode2Parent;

        public ReturnType(Node curNode, boolean isNode1Parent,boolean isNode2Parent) {
            this.curNode = curNode;
            this.isNode1Parent = isNode1Parent;
            this.isNode2Parent = isNode2Parent;
        }
    }

    /**
     * 若 isNode1Parent && isNode2Parent,直接向上透传,不会再改变 ReturnType 中 curNode 的值;
     * 否则 "true" 不断向上富集,同时修改 ReturnType 中 curNode 为当前节点.
     * @param head
     * @param node1
     * @param node2
     * @return
     */
    public static ReturnType process(Node head, Node node1, Node node2) {
        if(head==null)
            return null;
        boolean isNode1Parent = false;
        boolean isNode2Parent = false;
        if(head==node1)
            isNode1Parent = true;
        else if(head==node2)
            isNode2Parent = true;

        ReturnType leftResult = process(head.left, node1, node2);
        ReturnType rightResult = process(head.right, node1, node2);

        if(leftResult!=null){
            if(leftResult.isNode1Parent && leftResult.isNode2Parent)
                return leftResult;
            else if(leftResult.isNode1Parent)
                isNode1Parent = true;
            else if(leftResult.isNode2Parent)
                isNode2Parent = true;
        }

        if(rightResult!=null) {
            if (rightResult.isNode1Parent && rightResult.isNode2Parent)
                return rightResult;
            else if (rightResult.isNode1Parent)
                isNode1Parent = true;
            else if (rightResult.isNode2Parent)
                isNode2Parent = true;
        }
        return new ReturnType(head, isNode1Parent, isNode2Parent);
    }
}
