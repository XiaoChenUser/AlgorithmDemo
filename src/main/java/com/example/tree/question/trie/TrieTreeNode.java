package com.example.tree.question.trie;

/**
 * 字符 a~z 依次对应 nexts 的 index 0~25.
 */
public class TrieTreeNode {
    public int pass;
    public int end;
    public TrieTreeNode[] nexts;
    //这里假定都是小写字母，若字符数较多，使用 HashMap
    //public HashMap<Char,TrieTreeNode> nexts;
    //public TreeMap<Char,TrieTreeNode> nexts;

    public TrieTreeNode() {
        this.pass = 0;
        this.end = 0;
        this.nexts = new TrieTreeNode[26];
    }
}
